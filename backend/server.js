const express = require("express");
const path = require("path");

const app = express();

const PORT = 4000;

app.use(express.static(path.join(__dirname, "frontend")));

app.get("/", (req, res) => {
    res.send("Trang chủ");
});

app.get("/login", (req, res) => {
    res.sendFile(path.join(__dirname, "frontend", "login.html"));
});

app.get("/register", (req, res) => {
    res.send("Trang đăng ký");
});

app.get("/exams", (req, res) => {
    res.send("Danh sách bài thi");
});

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});