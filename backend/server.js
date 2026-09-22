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

const users = [

    {
        id: 1,
        username: "dung",
        role: "student"
    },

    {
        id: 2,
        username: "teacher",
        role: "teacher"
    }

];
// Middleware

app.use(express.urlencoded({ extended: true }));
app.use(express.json());

app.use(
    express.static(
        path.join(__dirname, "..", "frontend")
    )
);

app.post("/api/users", (req, res) => {

    console.log(req.body);

    res.json(req.body);

});

// Routes
app.get("/api/profile", (req, res) => {

    res.json({
        id: 1,
        username: "dung",
        role: "student"
    });

});

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

app.post("/api/users", (req, res) => {

    console.log(req.body);

    const newUser = {
        id: users.length + 1,
        username: req.body.username,
        role: req.body.role
    };

    users.push(newUser);

    res.json(newUser);

});
app.get("/api/users", (req, res) => {

    res.json(users);

});

app.get("/api/users/:id", (req, res) => {

    const id = Number(req.params.id);

    const user = users.find(
        user => user.id === id
    );

    res.json(user);

});

app.put("/api/users/:id", (req, res) => {

    const id = Number(req.params.id);

    const user = users.find(
        user => user.id === id
    );

    if (!user) {
        return res.status(404).json({
            message: "User not found"
        });
    }

    user.username = req.body.username;
    user.role = req.body.role;

    res.json(user);

});

app.delete("/api/users/:id", (req, res) => {

    const id = Number(req.params.id);

    const index = users.findIndex(
        user => user.id === id
    );

    if (index === -1) {

        return res.status(404).json({
            message: "User not found"
        });

    }

    users.splice(index, 1);

    res.json({
        message: "Deleted successfully"
    });

});
// Start server

app.listen(PORT, () => {
    console.log(`Server running on port ${PORT}`);
});