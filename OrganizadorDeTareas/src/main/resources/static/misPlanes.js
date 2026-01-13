const proxPlanes = document.getElementById("proxPlanes")


proxPlanes.addEventListener("click",e => {
    const card = e.target.closest(".tarjetaPlan")
    if (!card) return

    window.location.href = `/plan.html?id=${card.id}`
})

const rowFut = document.getElementById("proxPlanes")
const rowPas = document.getElementById("planesPasados")
let html1 = ''
let html2 = ''

fetch(`/api/planes`,{
    method: 'GET'
})
.then(response => {
    if(!response.ok){
        throw new Error("Error en la respuesta")
    }

    return response.json();
})
.then(planes => {

    planes.forEach(p => {
        let fech = new Date(`${p.fecha}T${p.hora}`);
        let ahora = new Date();

        if(fech > ahora){
            html1 += 
            `<div class="col">
              <div id="${p.id}" class="card tarea tarjetaPlan" clickable>
                <span class="tag ${p.completo? 'completo':'incompleto'}">${p.completo? 'COMPLETO':'INCOMPLETO'}</span>
                <div>
                    <h5 class="card-title">${p.nombre}</h5>
                </div>
                <div class="card-body infPlan">
                    <p>Fecha: ${p.fecha}</p>
                    <p class="planHr">Hora: ${p.hora}</p>
                </div>
              </div>
            </div>`
        }else{
            html2 += 
            `<div class="col">
              <div class="card tarea" >
                <div>
                    <h5 class="card-title">${p.nombre}</h5>
                </div>
                <div class="card-body infPlan">
                    <p>Fecha: ${p.fecha}</p>
                    <p class="planHr">Hora: ${p.hora}</p>
                </div>
              </div>
            </div>`
        }
    })

    rowFut.innerHTML = html1
    rowPas.innerHTML = html2

})

