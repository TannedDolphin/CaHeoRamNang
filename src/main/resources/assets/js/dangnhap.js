document.getElementById('loginBtn').addEventListener('click', function() {
    const emailPhone = document.getElementById('loginEmailPhone').value;
    const password = document.getElementById('loginPassword').value;

    const requestData = {
        username: emailPhone,
        password: password
    };

    fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData),
    })
        .then(response => response.json())
        .then(data => {
            if (data.message === "Success") {
                alert("Đăng nhập thành công!");
            } else {
                alert("Đăng nhập thất bại!");
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
});
