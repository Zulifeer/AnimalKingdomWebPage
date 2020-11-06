import $ from 'jquery';
import * as THREE from 'three';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader.js';
import {OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';
$(document).ready(function () {

    var container, control, mixer, clock;
    var camera, scene, renderer, model;


    container = document.getElementById("3d-model-viewer");
    const w = container.offsetWidth;
    const h = container.offsetHeight;

    scene = new THREE.Scene();
    clock = new THREE.Clock();

    camera = new THREE.PerspectiveCamera(75, w / h, 0.75, 100);
    camera.position.set(1, 1, 20);
    camera.lookAt(scene.position);

    renderer = new THREE.WebGLRenderer({
        antialias: true
      }); 
    renderer.setClearColor("white");
    renderer.setSize(w, h);
    container.appendChild(renderer.domElement);

    control = new OrbitControls(camera, renderer.domElement);
    control.update();
    const abint = new THREE.AmbientLight("#CCC",4);
    scene.add(abint);

    var gltf_loader = new GLTFLoader();
    gltf_loader.load('Animal_3D_Scenes/model_54a_-_caribbean_reef_shark/scene.gltf',
        function(gltf){
            model = gltf.scene;
            mixer = new THREE.AnimationMixer(gltf.scene);
            var action = mixer.clipAction(gltf.animations[0]);
            action.play();
            scene.add(gltf.scene);

            console.log("Adding gltf.scene");
        },
        function(xhr){
            console.log( ( xhr.loaded / xhr.total * 100 ) + '% loaded' );
        },
        function(error){
            console.error("Error has happened: " + error);
        });



    function animate(){
        requestAnimationFrame(animate);
        var delta = clock.getDelta();
        if(model)mixer.update(delta);
        renderer.render(scene, camera);
        
    }
    animate();
    

}); 