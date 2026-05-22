/**
 * Toggles visibility of 'Other' input based on dropdown selection
 */
function toggleOtherDisease() {
    const select = document.getElementById('diseaseSelect');
    const otherGroup = document.getElementById('otherDiseaseGroup');
    const otherInput = document.getElementById('otherDiseaseInput');

    if (select.value === 'Other') {
        otherGroup.style.display = 'flex';
        otherInput.required = true;
    } else {
        otherGroup.style.display = 'none';
        otherInput.required = false;
        otherInput.value = '';
    }
}

/**
 * Dark Mode Toggle Logic
 */
document.addEventListener('DOMContentLoaded', function() {
    const toggle = document.getElementById('darkModeToggle');
    if (toggle) {
        toggle.addEventListener('click', () => {
            document.body.classList.toggle('dark-mode');
            toggle.textContent = document.body.classList.contains('dark-mode') ? 'Light' : 'Dark';
        });
    }

    // Form Submit Logic for 'Other' disease
    const form = document.getElementById('patientForm');
    if (form) {
        form.addEventListener('submit', function(e) {
            const select = document.getElementById('diseaseSelect');
            const otherInput = document.getElementById('otherDiseaseInput');

            if (select.value === 'Other' && otherInput.value.trim() !== '') {
                // Inject the custom value into the select to send to backend
                const customOption = document.createElement('option');
                customOption.value = otherInput.value;
                customOption.text = otherInput.value;
                customOption.selected = true;
                select.appendChild(customOption);
            }
        });
    }
});