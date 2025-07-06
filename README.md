# 🔐 Ứng Dụng Mã Hóa & Giải Mã Đa Thuật Toán Bằng Java

Một ứng dụng mạnh mẽ viết bằng Java, hỗ trợ **mã hóa và giải mã dữ liệu** theo nhiều thuật toán kinh điển trong lĩnh vực bảo mật. Giao diện thân thiện, dễ dùng, hỗ trợ thao tác với cả văn bản và tệp tin.

---

## 🚀 Tính Năng Nổi Bật

✅ Mã hóa & giải mã bằng các thuật toán:

* **Bêmh (Hashing):** MD5, SHA-1, SHA-256, SHA-512
* **Đối xứng:** AES, DES, Triple DES (3DES)
* **Bất đối xứng:** RSA (mã hóa văn bản & file)
* Và nhiều thuật toán khác...

✅ Hỗ trợ đầu vào:

* Văn bản trực tiếp
* File văn bản / tài liệu
* File nhị phân (image, zip, v.v.)

✅ Giao diện thân thiện (Swing/JavaFX)

* Giao diện rõ ràng, trực quan cho người dùng không chuyên

✅ Xuất kết quả:

* Xem trực tiếp
* Lưu file mã hóa
* Copy kết quả

✅ Đóng gói sẵn `.jar` để chạy ngay

---

## 📂 Cấu Trúc Thư Mục Dự Án

```bash
📆 java-encryption-suite
🗃️ src/                 # Mã nguồn Java
🗃️ jar/                 # File .jar build sẵn
🗃️ docs/                # Hình ảnh giao diện, sơ đồ hệ thống
🗃️ samples/             # File mẫu để test mã hóa
📄 README.md            # Tài liệu này
📄 GUIDE.pdf            # Hướng dẫn sử dụng chi tiết
```

---

## 💠 Cách Sử Dụng

### ▶️ Chạy ứng dụng (không cần code)

> Yêu cầu: Java 8+ đã cài sẵn

```bash
java -jar jar/encryptor-app.jar
```

👉 Giao diện sẽ hiện ra để sử dụng.

---

### 🧪 Một số ví dụ

| Thuật toán | Văn bản mẫu      | Kết quả đầu ra (rút gọn)          |
| ---------- | ---------------- | --------------------------------- |
| MD5        | `password123`    | `482c811da5d5b4bc6d497ffa...`     |
| AES        | File `notes.txt` | Mã hóa và lưu file `.aes`         |
| RSA        | `Hello World!`   | Chuỗi Base64 dài hoặc file `.rsa` |

---

## 📸 Giao Diện (nếu có)

![Screenshot](docs/ui.png)

---

## 📚 Tài Liệu Hướng Dẫn

Xem file `GUIDE.pdf` trong thư mục gốc để biết chi tiết cách dùng từng tính năng.

---

## 💡 Mục Tiêu & Ứng Dụng

* Học tập và thực hành kiến thức mã hóa trong Java
* Dùng để giảng dạy / demo bảo mật cơ bản
* Tạo tool mã hóa dữ liệu nội bộ cá nhân/doanh nghiệp nhỏ

---

## 📌 Tác Giả

👤 **Nguyễn Như Hoàn**
📧 [honhuhoan1305@gmail.com](mailto:honhuhoan1305@gmail.com)
🔗 [github.com/honhuhoan1305](https://github.com/honhuhoan1305)

---

## ✨ Gợi Ý Mở Rộng (tương lai)

* Kết nối với cloud để lưu trữ key
* Mã hóa theo batch file / folder
* Giao diện dark mode + responsive hơn
* REST API hỗ trợ client khác (Python/JS...)
