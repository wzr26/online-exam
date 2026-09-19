const express = require("express");
const path = require("path");

const app = express();

app.use((req, res, next) => {

    console.log("==============");
    console.log("METHOD:", req.method);
    console.log("URL:", req.url);
    console.log("==============");

    next();

});

const PORT = 4000;

// Middleware

app.use(express.urlencoded({ extended: true }));

app.use(
    express.static(
        path.join(__dirname, "..", "frontend")
    )
);

// Routes

app.get("/", (req, res) => {
    res.send("Trang chủ");
});


app.get("/login", (req, res) => {
    res.sendFile(
        path.join(
            __dirname,
            "..",
            "frontend",
            "login.html"
        )
    );
});


app.post("/login", (req, res) => {

    console.log(req.body);

    res.send("Đã nhận dữ liệu");

});


app.get("/register", (req, res) => {
    res.send("Trang đăng ký");
});


app.get("/exams", (req, res) => {
    res.send("Danh sách bài thi");
});


app.get("/profile", (req, res) => {
    res.send("Trang cá nhân");
});

// Test params

app.get("/exams/:id", (req, res) => {

    const id = req.params.id;

    res.send(`Exam ID: ${id}`);

});

// Start server

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});