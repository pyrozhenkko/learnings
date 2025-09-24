import socket
import cv2
import numpy as np
import struct
from ultralytics import YOLO
import threading
import time

HOST = "0.0.0.0"
PORT = 9999

def recv_all(sock, length):
    """Отримуємо рівно length байтів"""
    data = b''
    while len(data) < length:
        packet = sock.recv(length - len(data))
        if not packet:
            return None
        data += packet
    return data

# Завантажуємо YOLOv8
model = YOLO("yolov8n.pt")

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind((HOST, PORT))
server_socket.listen(1)

print(f"[INFO] Очікування з'єднання на {HOST}:{PORT}...")
conn, addr = server_socket.accept()
print(f"[INFO] Підключився клієнт: {addr}")

# Повноекранне вікно
cv2.namedWindow("YOLOv8 Live", cv2.WINDOW_NORMAL)
cv2.setWindowProperty("YOLOv8 Live", cv2.WND_PROP_FULLSCREEN, cv2.WINDOW_FULLSCREEN)

frame = None
results = None
lock = threading.Lock()

# Функція для обробки кадрів кожні 2 секунди
def process_frame():
    global frame, results
    while True:
        if frame is not None:
            with lock:
                frame_copy = frame.copy()
            # Аналіз кадру YOLO
            results_local = model(frame_copy)[0]
            with lock:
                results = results_local
        time.sleep(2)  # чекаємо 2 секунди перед наступним аналізом

# Запускаємо потік
threading.Thread(target=process_frame, daemon=True).start()

while True:
    length_bytes = recv_all(conn, 4)
    if not length_bytes:
        break
    length = struct.unpack('!I', length_bytes)[0]

    frame_data = recv_all(conn, length)
    if frame_data is None:
        break

    nparr = np.frombuffer(frame_data, np.uint8)
    new_frame = cv2.imdecode(nparr, cv2.IMREAD_COLOR)

    if new_frame is not None:
        with lock:
            frame = new_frame.copy()
            local_results = results  # останні оброблені результати

        # Малюємо прямокутники на кадрі
        if local_results is not None:
            for r in local_results.boxes:
                x1, y1, x2, y2 = map(int, r.xyxy[0])
                conf = float(r.conf[0])
                cls_id = int(r.cls[0])
                label = model.names[cls_id]

                cv2.rectangle(frame, (x1, y1), (x2, y2), (0,255,0), 2)
                cv2.putText(frame, f"{label} {conf:.2f}", (x1, y1-10),
                            cv2.FONT_HERSHEY_SIMPLEX, 0.7, (0,255,0), 2)

        cv2.imshow("YOLOv8 Live", frame)
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break

conn.close()
server_socket.close()
cv2.destroyAllWindows()
