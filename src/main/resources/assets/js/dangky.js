document.getElementById('registerBtn').addEventListener('click', function() {
    const emailPhone = document.getElementById('registerEmailPhone').value;
    const password = document.getElementById('registerPassword').value;

    const requestData = {
        emailPhone: emailPhone,
        password: password
    };

    fetch('http://localhost:8080/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestData),
    })
        .then(response => response.json())
        .then(data => {
            if (data.message === "Success") {
                alert("Đăng ký thành công!");
            } else {
                alert("Đăng ký thất bại!");
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
});
