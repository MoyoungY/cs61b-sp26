import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.StdDraw;

public class ParticleSimulator {

    public static final Map<Character, ParticleFlavor> LETTER_TO_PARTICLE = Map.of(
        's', ParticleFlavor.SAND,
        'b', ParticleFlavor.BARRIER,
        'w', ParticleFlavor.WATER,
        'p', ParticleFlavor.PLANT,
        'f', ParticleFlavor.FIRE,
        '.', ParticleFlavor.EMPTY,
        'n', ParticleFlavor.FOUNTAIN,
        'r', ParticleFlavor.FLOWER
    );

    public Particle[][] particles;
    public int width;
    public int height;

    public ParticleSimulator(int w, int h) {
        width = w;
        height = h;
        particles = new Particle[width][height];

        for (Particle[] pw: particles){
            for (int i = 0; i < pw.length; i++) {
                pw[i] = new Particle(ParticleFlavor.EMPTY);
            }
        }
    }

    public void drawParticles() {
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                StdDraw.setPenColor(particles[x][y].color());
                StdDraw.filledSquare(x, y, 0.5);
            }
        }
    }

    public boolean validIndex(int x, int y) {
        return (0 <=x && x < width) && (0 <= y && y < height);
    }

    public Map<Direction, Particle> getNeighbors(int x, int y) {
        Map<Direction, Particle>  neighborMap = new HashMap<>();

        // UP
        if(validIndex(x, y+1)){
            neighborMap.put(Direction.UP, particles[x][y+1]);
        }else {
            neighborMap.put(Direction.UP, new Particle(ParticleFlavor.BARRIER));
        }

        // Down
        if(validIndex(x, y-1)){
            neighborMap.put(Direction.DOWN, particles[x][y-1]);
        }else {
            neighborMap.put(Direction.DOWN, new Particle(ParticleFlavor.BARRIER));
        }

        // LEFT
        if(validIndex(x-1, y)){
            neighborMap.put(Direction.LEFT, particles[x-1][y]);
        }else {
            neighborMap.put(Direction.LEFT, new Particle(ParticleFlavor.BARRIER));
        }

        // RIGHT
        if(validIndex(x+1, y)){
            neighborMap.put(Direction.RIGHT, particles[x+1][y]);
        }else {
            neighborMap.put(Direction.RIGHT, new Particle(ParticleFlavor.BARRIER));
        }

        return neighborMap;
    }

    public void tick() {
        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                particles[i][j].action(getNeighbors(i, j));
                particles[i][j].decrementLifespan();
            }
        }
    }

    @Override
    public String toString() {
        // 1. Build a reverse map to look up characters by Flavor
        Map<ParticleFlavor, Character> flavorToChar = new HashMap<>();
        for (Map.Entry<Character, ParticleFlavor> entry : LETTER_TO_PARTICLE.entrySet()) {
            flavorToChar.put(entry.getValue(), entry.getKey());
        }

        StringBuilder sb = new StringBuilder();

        // Have to iterate from the top so that
        // the top particles are shown first.
        for (int y = height - 1; y >= 0; y -= 1) {
            for (int x = 0; x < width; x += 1) {
                Particle p = particles[x][y];
                sb.append(flavorToChar.get(p.flavor));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ParticleSimulator particleSimulator = new ParticleSimulator(150, 150);
        StdDraw.setXscale(0, particleSimulator.width);
        StdDraw.setYscale(0, particleSimulator.height);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        ParticleFlavor nextParticleFlavor = ParticleFlavor.SAND;

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                Character key = StdDraw.nextKeyTyped();
                nextParticleFlavor = LETTER_TO_PARTICLE.get(key);
                if (nextParticleFlavor == null) {
                    nextParticleFlavor = ParticleFlavor.EMPTY;
                }
            }

            if (StdDraw.isMousePressed()) {
                int x = (int) StdDraw.mouseX();
                int y = (int) StdDraw.mouseY();
                if(particleSimulator.validIndex(x, y)){
                    particleSimulator.particles[x][y] = new Particle(nextParticleFlavor);
                }
            }

            particleSimulator.tick();
            particleSimulator.drawParticles();
            StdDraw.show();
            StdDraw.pause(5);
        }
    }

    


}
