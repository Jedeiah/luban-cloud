const jwtTokenName = "AuthorizationJwt";


document.addEventListener('DOMContentLoaded', function () {
    fetchAndDisplayProducts();

    // 新增产品表单提交事件
    $('#newProductForm').submit(function (event) {
        event.preventDefault();
        var productName = $('#productName').val();
        addProduct(productName);
    });
    // 更新产品表单提交事件
    $('#editProductModal').submit(function (event) {
        event.preventDefault();
        var productId = $('#updateProductId').val();
        var productName = $('#updateProductName').val();
        updateProduct(productId, productName)

    });
});

function fetchAndDisplayProducts() {
    // 使用Fetch API发起GET请求，并设置请求头
    fetch('http://192.168.0.151:8890/api/product/products/listAll', {
        headers: {
            [jwtTokenName]: getCookie(jwtTokenName)
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
                        <span id="productId${product.id}">${product.id}</span>
                        <span id="productName${product.id}">${product.name}</span>
                        <button onclick="deleteProduct(${product.id})">删除</button>
                        <button id="editProductButton${product.id}", onclick="editProduct(${product.id},'${product.name}')">修改</button>
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


function logout() {
    var date = new Date();
    date.setTime(date.getTime() - (1 * 24 * 60 * 60 * 1000)); // 设置时间为过去一天
    var expires = "expires=" + date.toUTCString();
    document.cookie = jwtTokenName + "=; " + expires + "; path=/";
    window.location.href = "login.html";
}

function deleteProduct(productId) {
    // 使用 Fetch API 发起 DELETE 请求
    fetch(`http://192.168.0.151:8890/api/product/products/delete/${productId}`, {
        method: 'DELETE', // 设置请求方法为 DELETE
        headers: {
            [jwtTokenName]: getCookie(jwtTokenName)
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


function editProduct(productId, productName) {

    // 填充模态框中的表单
    $('#editProductModal').modal('show');
    $('#editProductModalLabel').text('修改产品');
    $('#updateProductId').val(productId);
    $('#updateProductName').val(productName);

}

function addProduct(productName) {
    // 发送 POST 请求到服务器
    fetch('http://192.168.0.151:8890/api/product/products/add', {
        method: 'POST',
        headers: {
            [jwtTokenName]: getCookie(jwtTokenName),
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({name: productName})
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

function updateProduct(productId, newName) {

    // 使用 Fetch API 发起 PUT 请求
    fetch(`http://192.168.0.151:8890/api/product/products/update`, {
        method: 'PUT',
        headers: {
            [jwtTokenName]: getCookie(jwtTokenName),
            'Content-Type': 'application/json'
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