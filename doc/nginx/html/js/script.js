document.addEventListener('DOMContentLoaded', function () {
    // 登录成功后调用此函数
    function fetchAndDisplayProducts() {
        var authorizationJwt = getCookie('AuthorizationJwt');
        // 使用Fetch API发起GET请求，并设置请求头
        fetch('http://192.168.0.151:8890/api/product/products/listAll', {
            headers: {
                'AuthorizationJwt': authorizationJwt
            }
        })
            .then(response => {
                if (!response.ok) {
                    if (response.status === 401 || response.status === 405) {
                        window.location.href = 'login.html';
                    }
                    throw new Error(response.statusText);
                }
                return response.json();
            })
            .then(data => {
                if (data.success) {
                    const productListContainer = document.getElementById('productListContainer');
                    data.data.forEach(product => {
                        // 创建产品列表项
                        const productItem = document.createElement('div');
                        productItem.className = 'product-item';
                        productItem.innerHTML = `
                        <h3>ID: <span id="productId${product.id}">${product.id}</span></h3>
                        <h3>名称: <span id="productName${product.id}">${product.name}</span></h3>
                        <button onclick="deleteProduct(${product.id})">删除</button>
                        <button id="editProductButton${product.id}", onclick="editProduct(${product.id})">修改</button>
                        `;
                        productListContainer.appendChild(productItem);
                    });
                } else {
                    alert(data.errMsg);
                }
            })
            .catch(error => {
                alert(error.message);
            });
    }

    // 调用函数以填充产品列表
    fetchAndDisplayProducts();
});

// 获取cookie的函数
function getCookie(name) {
    var cookieValue = null;
    if (document.cookie && document.cookie !== '') {
        var cookies = document.cookie.split(';');
        for (var i = 0; i < cookies.length; i++) {
            var cookie = jQuery.trim(cookies[i]);
            // does this cookie string begin with the name we want?
            if (cookie.substring(0, name.length + 1) === (name + '=')) {
                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                break;
            }
        }
    }
    return cookieValue;
}

function logout(name) {
    var date = new Date();
    date.setTime(date.getTime() - (1 * 24 * 60 * 60 * 1000)); // 设置时间为过去一天
    var expires = "expires=" + date.toUTCString();
    document.cookie = name + "=; " + expires + "; path=/";
    window.location.href = "login.html";
}
function deleteProduct(productId) {
    // 使用 Fetch API 发起 DELETE 请求
    fetch(`http://192.168.0.151:8890/api/product/products/delete/${productId}`, {
        method: 'DELETE', // 设置请求方法为 DELETE
        headers: {
            'AuthorizationJwt': getCookie('AuthorizationJwt') // 设置请求头
        }
    })
        .then(response => {
            if (!response.ok) {
                if (response.status === 401 || response.status === 405) {
                    window.location.href = 'login.html';
                }
                throw new Error(response.statusText);
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                alert(data.data);
                window.location.reload();
            } else {
                alert(data.errMsg);
            }
        })
        .catch(error => {
            alert(error.message);
        });
}


function editProduct(productId) {
    console.log(productId);

}

function updateProduct(productId, newName) {

    // 使用 Fetch API 发起 PUT 请求
    fetch(`http://192.168.0.151:8890/api/product/products/update/${productId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'AuthorizationJwt': getCookie('AuthorizationJwt')
        },
        body: JSON.stringify({
            "id": productId,
            "name": newName
        })
    })
        .then(response => {
            if (!response.ok) {
                if (response.status === 401 || response.status === 405) {
                    window.location.href = 'login.html';
                }
                throw new Error(response.statusText);
            }
            return response.json();
        })
        .then(data => {
            if (data.success) {
                alert(data.data);
                window.location.reload();
            } else {
                alert(data.errMsg);
            }
        })
        .catch(error => {
            alert(error.message);
        });
}