document.addEventListener('DOMContentLoaded', function() {
    const orderButtons = document.querySelectorAll('.order-btn');

    orderButtons.forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault(); // 防止可能的默认行为

            const buttonElement = this; // 保存引用
            const foodId = buttonElement.getAttribute('data-food-id');

            console.log('开始预定，foodId:', foodId);

            // 显示加载状态
            const originalText = buttonElement.innerHTML;
            buttonElement.innerHTML = '<i class="fas fa-spinner fa-spin"></i> 处理中...';
            buttonElement.disabled = true;

            fetch('/home/order', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    foodId: foodId
                })
            })
                .then(response => {
                // 恢复按钮状态
                buttonElement.innerHTML = originalText;
                buttonElement.disabled = false;

                console.log('响应状态:', response.status, response.ok);

                if (response.ok) {
                    // 检查Swal是否可用
                    if (typeof Swal === 'undefined') {
                        alert('预约成功！页面即将刷新...');
                        window.location.reload();
                        return;
                    }

                    // 显示成功弹窗
                    Swal.fire({
                        title: '🎉 预约成功！',
                        html: `
                            <div style="text-align: center;">
                                <i class="fas fa-check-circle" style="font-size: 48px; color: #28a745; margin-bottom: 20px;"></i>
                                <p>您的预约已成功提交</p>
                                <p>确认邮件已发送到您的邮箱</p>
                            </div>
                        `,
                        icon: 'success',
                        confirmButtonText: '确定',
                        showCancelButton: false,
                        allowOutsideClick: false,
                        allowEscapeKey: false
                    }).then((result) => {
                        if (result.isConfirmed) {
                            window.location.reload();
                        }
                    });
                } else {
                    // HTTP错误
                    if (typeof Swal === 'undefined') {
                        alert('预约失败，请重试');
                        return;
                    }

                    Swal.fire({
                        title: '❌ 预约失败',
                        text: '服务器处理请求时出错',
                        icon: 'error',
                        confirmButtonText: '确定'
                    });
                }
            })
                .catch(error => {
                // 恢复按钮状态
                buttonElement.innerHTML = originalText;
                buttonElement.disabled = false;

                console.error('网络错误:', error);

                if (typeof Swal === 'undefined') {
                    alert('网络错误，请检查连接');
                    return;
                }

                Swal.fire({
                    title: '⚠️ 网络错误',
                    text: '请检查网络连接后重试',
                    icon: 'warning',
                    confirmButtonText: '确定'
                });
            });
        });
    });
});