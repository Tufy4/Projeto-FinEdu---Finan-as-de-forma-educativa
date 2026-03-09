document.addEventListener('DOMContentLoaded', function () {
    const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]');
    
    popoverTriggerList.forEach(popoverTriggerEl => {
        const id = popoverTriggerEl.getAttribute('data-bs-id');
        const contentEl = document.getElementById('popover-content-' + id);
        
        const popover = new bootstrap.Popover(popoverTriggerEl, {
            html: true,
            content: contentEl.innerHTML,
            placement: 'top',
            trigger: 'click',
            customClass: 'shadow-sm border-success'
        });

   
        popoverTriggerEl.addEventListener('click', function () {
            popoverTriggerList.forEach(el => {
                if (el !== popoverTriggerEl) {
                    const instance = bootstrap.Popover.getInstance(el);
                    if (instance) instance.hide();
                }
            });
        });
    });

   
    document.addEventListener('click', function (e) {
        if (e.target.classList.contains('close-popover')) {
            const popoverId = e.target.closest('.popover').id;
            const triggerEl = document.querySelector(`[aria-describedby="${popoverId}"]`);
            if (triggerEl) {
                bootstrap.Popover.getInstance(triggerEl).hide();
            }
        }
    });
});