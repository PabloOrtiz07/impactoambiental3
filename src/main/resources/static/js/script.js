function mostrarMiembro() {
    document.getElementById('botones').style.display = 'none';
    document.getElementById('miembro').style.display = '';
    document.getElementById('organizacion').style.display = 'none';
    document.getElementById('agente-sectorial').style.display = 'none';
    document.getElementById('atras').style.display = '';
}

function mostrarOrganizacion() {
    document.getElementById('botones').style.display = 'none';
    document.getElementById('miembro').style.display = 'none';
    document.getElementById('organizacion').style.display = '';
    document.getElementById('agente-sectorial').style.display = 'none';
    document.getElementById('atras').style.display = '';
}

function mostrarAgente() {
    document.getElementById('botones').style.display = 'none';
    document.getElementById('miembro').style.display = 'none';
    document.getElementById('organizacion').style.display = 'none';
    document.getElementById('agente-sectorial').style.display = '';
    document.getElementById('atras').style.display = '';
}

function mostrarSelector() {
    document.getElementById('botones').style.display = 'none';
    document.getElementById('miembro').style.display = 'none';
    document.getElementById('organizacion').style.display = 'none';
    document.getElementById('agente-sectorial').style.display = 'none';
    document.getElementById('atras').style.display = 'none';
    document.getElementById('botones').style.display = '';
}

window.setTimeout(function() {
    $(".alert").fadeTo(500, 0).slideUp(500, function(){
        $(this).remove(); 
    });
}, 4000);