const fs = require('fs');

let obtenerPokemons = async() => {
    let pokemons = [];
    for (let i = 1; i < 1200; i++) {
        if(i%100 == 0)
            console.log(i);
        let fet;
        let poke
        try{
        fet = await fetch('https://pokeapi.co/api/v2/pokemon/' + i);
        poke = await fet.json();
        }catch(e){
            console.log(i);
            fet = undefined;
        }
        if (fet != undefined){
            let objeto = new Object();
            objeto.name = poke.name;
            objeto.id = poke.id;
            objeto.img = poke.sprites.front_default;
            objeto.imgS = poke.sprites.front_shiny;

            pokemons.push(objeto);
        }
    }
    console.log(JSON.stringify(pokemons));
    fs.writeFile("pokemons.json", JSON.stringify(pokemons), 'utf8', function(err) {
        if (err)
            console.log("ERROR")
        else
            console.log("Guardado")
    })
}

obtenerPokemons()