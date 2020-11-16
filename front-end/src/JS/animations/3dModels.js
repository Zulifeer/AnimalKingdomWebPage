import $ from 'jquery';
import * as THREE from 'three';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader.js';
import {OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';
$(document).ready(function () {

    var container, control, mixer, clock;
    var camera, scene, renderer, model;

    container = document.getElementById("3d-model");

    let gltf_loader = new GLTFLoader();

    if(container != null){
        var w = container.offsetWidth;
        var h = container.offsetHeight;
        scene = new THREE.Scene();
        clock = new THREE.Clock();
        camera = new THREE.PerspectiveCamera(45, w / h, 0.1, 2000);
        camera.position.set(-2, 2, 10);
        camera.lookAt(scene.position);

        renderer = new THREE.WebGLRenderer({
            alpha: true
        }); 
        renderer.setClearColor( 0xff0000, 0 );
        renderer.setPixelRatio(window.devicePixelRatio);
        renderer.setSize(w, h);
        container.appendChild(renderer.domElement);

        control = new OrbitControls(camera, renderer.domElement);
        control.update();
        var abint = new THREE.AmbientLight(0xffffff);
        scene.add(abint);

        
        gltf_loader.load(model_path,
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

    }
    
    

}); 