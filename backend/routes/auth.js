const express = require("express");

const router = express.Router();

router.get("/login", (req, res) => {
    res.sendFile(
        require("path").join(
            __dirname,
            "..",
            "..",
            "frontend",
            "login.html"
        )
    );
});

router.post("/login", (req, res) => {

    console.log(req.body);

    res.send("Đã nhận dữ liệu");

});

router.get("/register", (req, res) => {
    res.send("Trang đăng ký");
});

module.exports = router;