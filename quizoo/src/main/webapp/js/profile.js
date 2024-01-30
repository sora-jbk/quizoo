/**
 * 
 */

document.addEventListener('DOMContentLoaded', function () {
    // �n���o�[�K�[�{�^���N���b�N�Ŏ��s
    document.querySelector(".drawer__button").addEventListener('click', function () {
        this.classList.toggle("active");
        document.querySelector(".drawer__nav").classList.toggle("active");
    });

    // �h�����[�i�r�Q�[�V���������N�N���b�N�Ŕ�A�N�e�B�u��
    document.querySelectorAll(".drawer__nav__link").forEach(function (link) {
        link.addEventListener('click', function () {
            document.querySelector(".drawer__button").classList.remove("active");
            document.querySelector(".drawer__nav").classList.remove("active");
        });
    });

    // �y�[�W���X�N���[��
    document.querySelectorAll('a[href^="#"]').forEach(function (anchor) {
        anchor.addEventListener('click', function (e) {
            e.preventDefault();

            const speed = 400;
            let href = this.getAttribute("href");
            let target = document.querySelector(href === "#" || href === "" ? "html" : href);
            let position = target.offsetTop;

            window.scrollTo({
                top: position,
                behavior: "smooth"
            });
        });
    });
});
