// scripts.js

document.addEventListener("DOMContentLoaded", () => {
    const container = document.getElementById('sphere');

    // Scene
    const scene = new THREE.Scene();

    // Camera
    const camera = new THREE.PerspectiveCamera(75, container.clientWidth / container.clientHeight, 0.1, 1000);
    camera.position.z = 5;

    // Renderer
    const renderer = new THREE.WebGLRenderer({ antialias: true });
    renderer.setSize(container.clientWidth, container.clientHeight);
    container.appendChild(renderer.domElement);

    // Textures
    const textureLoader = new THREE.TextureLoader();
    const imagePaths = [
        'images/image1.jpg',
        'images/image2.jpg',
        'images/image3.jpg',
        // Agrega más rutas de imágenes según sea necesario
    ];
    const textures = imagePaths.map(path => textureLoader.load(path));

    // Spheres
    const radius = 2;
    const sphericalGeometry = new THREE.SphereGeometry(0.5, 32, 32);
    
    textures.forEach((texture, index) => {
        const material = new THREE.MeshBasicMaterial({ map: texture });
        const sphere = new THREE.Mesh(sphericalGeometry, material);

        // Position spheres randomly on the surface of an imaginary sphere
        const phi = Math.acos(2 * Math.random() - 1);
        const theta = 2 * Math.PI * Math.random();

        sphere.position.x = radius * Math.sin(phi) * Math.cos(theta);
        sphere.position.y = radius * Math.sin(phi) * Math.sin(theta);
        sphere.position.z = radius * Math.cos(phi);

        scene.add(sphere);
    });

    // Animation
    function animate() {
        requestAnimationFrame(animate);
        scene.rotation.y += 0.005; // Rotate the entire scene for better visualization
        renderer.render(scene, camera);
    }
    animate();

    // Handle resize
    window.addEventListener('resize', () => {
        renderer.setSize(container.clientWidth, container.clientHeight);
        camera.aspect = container.clientWidth / container.clientHeight;
        camera.updateProjectionMatrix();
    });
});
