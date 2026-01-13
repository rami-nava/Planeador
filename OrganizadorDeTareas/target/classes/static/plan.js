const params = new URLSearchParams(window.location.search)
const id = params.get('id')
const nombre = document.getElementById("nombre")
const participantes = document.getElementById("participantes")
const lugar = document.getElementById("lugar")
const fecha = document.getElementById("fecha")
const hora = document.getElementById("hora")
const listado = document.getElementById("listado")


if(!id){
    window.location.href = '/'
}

fetch(`/api/planes/${id}`,{
    method: 'GET'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error en la respuesta');
        }
        return response.json();
    })
    .then(plan => {
        nombre.textContent = plan.nombre
        participantes.textContent += plan.participantes
        lugar.textContent += plan.lugar
        fecha.textContent += plan.fecha
        hora.textContent += plan.hora


        plan.tareas.forEach(t => {

            var encargados = t.encargados ? t.encargados : []
            var html = ""

            if(t.encargados.length > 0){
                
                for(var i = 0; i < t.encargados.length; i++){
                    html += 
                    ` <div class = "card encargado">
                            <div class="card-body responsables">
                                <div>
                                    <p>${t.encargados[i].nombre} ${t.encargados[i].apellido}</p>
                                    <p>${t.encargados[i].contacto}</p>
                                </div>
                                <h1 class="btnResponsable" data-tarea=${t.id} data-index="${i}">-</h1>
                            </div>
                        </div>`
                }
            }
            
            if(t.cantidad > t.encargados.length){    
                var cant = t.cantidad - t.encargados.length

                for(var i = 0; i < cant; i++){
                    html += 
                    `<div class = "card encargado">
                        <div class="card-body sinDef">
                            <p>Sin Definir</p>
                            <img id=${t.id} class="newUser" src="resources/new.png" alt="Descripción de la imagen">
                        </div>
                    </div>`
                }
            }



            listado.innerHTML += 
            `<div class="col">
              <div class="card tarea" >
                <h5 class="card-title">${t.nombre}</h5>
                <div class="card-body">
                ${html}
                </div>
              </div>
            </div>`;
        })
    });


const form = document.getElementById("formTareaNueva")
const plus = document.getElementById("new")
const modal = new bootstrap.Modal(document.getElementById("modalTareas"))


plus.addEventListener("click", ()=>{modal.show();})



form.addEventListener("submit", function (e) {
    e.preventDefault(); //evita recarga de pagina

    const tarea = {
        nombre: document.getElementById("nom").value,
        cantidad: parseInt(document.getElementById("res").value),
    };
   
    fetch(`/api/planes/${id}/tareas`, {
        method: 'POST',
        body: JSON.stringify(tarea),
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error en la respuesta');
        }
        
        window.location.href = `/plan.html?id=${id}`;
    })
    
});

const modalEnc = new bootstrap.Modal(document.getElementById("modalEncargados"))
let idTarea = null
let ind = null

listado.addEventListener("click", e => {
    if (e.target.classList.contains("newUser")) {
        idTarea = e.target.id
        modalEnc.show();
    }

    if(e.target.classList.contains("btnResponsable")){
        ind = e.target.dataset.index;
        idTarea = e.target.dataset.tarea;
        fetch(`/api/planes/${id}/tareas/${idTarea}/encargados/${ind}`, {
            method: 'POST'
        })
        .then(response => {
            if(!response.ok){
                throw new Error("Error en la respuesta");
            }

            window.location.href = `/plan.html?id=${id}`
        })
    }
});

const formEnc = document.getElementById("formEncargadoNuevo")

formEnc.addEventListener("submit", function (e) {
    e.preventDefault();
    
        const encargado = {
            nombre: document.getElementById("nombreEnc").value,
            apellido: document.getElementById("apellidoEnc").value,
            contacto: document.getElementById("contactoEnc").value
        };

       fetch(`/api/planes/${id}/tareas/${idTarea}`,{
        method: 'POST',
        body: JSON.stringify(encargado),
        headers: {
            'Content-Type': 'application/json'
        }
       })
       .then(response => {
            if(!response.ok){
                throw new Error('Error en la respuesta');
            }
            window.location.href = `/plan.html?id=${id}`
       })
});


