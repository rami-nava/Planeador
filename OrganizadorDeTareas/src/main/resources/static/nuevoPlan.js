const form = document.getElementById("formPlanNuevo")

form.addEventListener("submit", function (e) {
    e.preventDefault(); //evita recarga de pagina

    const plan = {
        nombre: document.getElementById("nombre").value,
        lugar: document.getElementById("lugar").value,
        participantes: parseInt(document.getElementById("participantes").value),
        fecha: document.getElementById("fecha").value,
        hora: document.getElementById("hora").value
    };

   
    fetch(`http://localhost:8080/api/planes`, {
        method: 'POST',
        body: JSON.stringify(plan),
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error en la respuesta');
        }
        return response.json();
    })
    .then(plan => {
        window.location.href = `plan.html?id=${plan.id}`;
    })
    .catch(err => {
        console.error('Error:', err);
    });
    

});


