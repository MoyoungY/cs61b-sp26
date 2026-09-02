import java.util.Map;

import edu.princeton.cs.algs4.StdRandom;

import java.awt.Color;

public class Particle {
    public static final int PLANT_LIFESPAN = 150;
    public static final int FLOWER_LIFESPAN = 75;
    public static final int FIRE_LIFESPAN = 10;
    public static final Map<ParticleFlavor, Integer> LIFESPANS =
            Map.of(ParticleFlavor.FLOWER, FLOWER_LIFESPAN,
                   ParticleFlavor.PLANT, PLANT_LIFESPAN,
                   ParticleFlavor.FIRE, FIRE_LIFESPAN);

    ParticleFlavor flavor;
    int lifespan;

    public Particle(ParticleFlavor pf) {
        flavor = pf;
        if (LIFESPANS.containsKey(pf)) {
            lifespan = LIFESPANS.get(pf);
        }
        else {
            lifespan = -1;
        }
    }

    public Color color() {
        if (flavor == ParticleFlavor.EMPTY){
            return Color.BLACK;
        } else if (flavor == ParticleFlavor.SAND) {
            return Color.YELLOW;
        } else if (flavor == ParticleFlavor.BARRIER) {
            return Color.GRAY;
        } else if (flavor == ParticleFlavor.WATER) {
            return Color.BLUE;
        } else if (flavor == ParticleFlavor.FOUNTAIN) {
            return Color.CYAN;
        } else if (flavor == ParticleFlavor.FLOWER) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FLOWER_LIFESPAN)) / FLOWER_LIFESPAN;
            int r = 120 + (int) Math.round((255 - 120) * ratio);
            int g = 70 + (int) Math.round((141 - 70) * ratio);
            int b = 80 + (int) Math.round((161 - 80) * ratio);
            return new Color(r, g, b);
        }
        if (flavor == ParticleFlavor.PLANT) {
            double ratio = (double) Math.max(0, Math.min(lifespan, PLANT_LIFESPAN)) / PLANT_LIFESPAN;
            int g = 120 + (int) Math.round((255 - 120) * ratio);
            return new Color(0, g, 0);
        }
        if (flavor == ParticleFlavor.FIRE) {
            double ratio = (double) Math.max(0, Math.min(lifespan, FIRE_LIFESPAN)) / FIRE_LIFESPAN;
            int r = (int) Math.round(255 * ratio);
            return new Color(r, 0, 0);
        }

        return null;
    }

    public void moveInto(Particle other) {
        other.flavor = this.flavor;
        other.lifespan = this.lifespan;

        this.flavor = ParticleFlavor.EMPTY;
        this.lifespan = -1;
    }

    public void fall(Map<Direction, Particle> neighbors) {
        if(neighbors.get(Direction.DOWN).flavor==ParticleFlavor.EMPTY){
            moveInto(neighbors.get(Direction.DOWN));
        }
    }

    public void action(Map<Direction, Particle> neighbors){
        if (this.flavor==ParticleFlavor.EMPTY) {
            return;
        }else if (this.flavor!=ParticleFlavor.BARRIER) {
            this.fall(neighbors);
            if (this.flavor==ParticleFlavor.WATER){
                this.flow(neighbors);
            } else if(this.flavor==ParticleFlavor.PLANT || this.flavor==ParticleFlavor.FLOWER){
                this.grow(neighbors);
            } else if(this.flavor==ParticleFlavor.FIRE){
                this.burn(neighbors);
            }
        }
    }

    public void flow(Map<Direction, Particle> neighbors) {
        int randomNum = StdRandom.uniformInt(3);
        if (randomNum==1) {return;}
        else if (randomNum==2) {
            if(neighbors.get(Direction.LEFT).flavor==ParticleFlavor.EMPTY){
                moveInto(neighbors.get(Direction.LEFT));
            }
        }
        else if (randomNum==3) {
            if(neighbors.get(Direction.RIGHT).flavor==ParticleFlavor.EMPTY){
                moveInto(neighbors.get(Direction.RIGHT));
            }
        }
    }

    public void grow(Map<Direction, Particle> neighbors){
        int randomNum = StdRandom.uniformInt(10);
        if(randomNum==1){
            if (neighbors.get(Direction.UP).flavor == ParticleFlavor.EMPTY){
                neighbors.get(Direction.UP).flavor = this.flavor;
                neighbors.get(Direction.UP).lifespan = LIFESPANS.get(this.flavor);
                
            }
        } else if(randomNum==2){
            if (neighbors.get(Direction.LEFT).flavor == ParticleFlavor.EMPTY){
                neighbors.get(Direction.LEFT).flavor = this.flavor;
                neighbors.get(Direction.LEFT).lifespan = LIFESPANS.get(this.flavor);
            }
        } else if(randomNum==3){
            if (neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.EMPTY){
                neighbors.get(Direction.RIGHT).flavor = this.flavor;
                neighbors.get(Direction.RIGHT).lifespan = LIFESPANS.get(this.flavor);
            }
        }
    }

    public void decrementLifespan(){
        if(this.lifespan>0){this.lifespan-=1;}
        else if(this.lifespan==0){
            this.flavor = ParticleFlavor.EMPTY;
            this.lifespan = -1;
        }
    }

    public void burn(Map<Direction, Particle> neighbors){
        if(neighbors.get(Direction.UP).flavor == ParticleFlavor.PLANT 
        || neighbors.get(Direction.UP).flavor == ParticleFlavor.FLOWER){
            int randomNum = StdRandom.uniformInt(10);
            if(randomNum <= 4){
                neighbors.get(Direction.UP).flavor = ParticleFlavor.FIRE;
                neighbors.get(Direction.UP).lifespan = FIRE_LIFESPAN;
            }
        }
        if(neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.PLANT 
        || neighbors.get(Direction.RIGHT).flavor == ParticleFlavor.FLOWER){
            int randomNum = StdRandom.uniformInt(10);
            if(randomNum <= 4){
                neighbors.get(Direction.RIGHT).flavor = ParticleFlavor.FIRE;
                neighbors.get(Direction.RIGHT).lifespan = FIRE_LIFESPAN;
            }
        }
        if(neighbors.get(Direction.DOWN).flavor == ParticleFlavor.PLANT 
        || neighbors.get(Direction.DOWN).flavor == ParticleFlavor.FLOWER){
            int randomNum = StdRandom.uniformInt(10);
            if(randomNum <= 4){
                neighbors.get(Direction.DOWN).flavor = ParticleFlavor.FIRE;
                neighbors.get(Direction.DOWN).lifespan = FIRE_LIFESPAN;
            }
        }
        if(neighbors.get(Direction.LEFT).flavor == ParticleFlavor.PLANT 
        || neighbors.get(Direction.LEFT).flavor == ParticleFlavor.FLOWER){
            int randomNum = StdRandom.uniformInt(10);
            if(randomNum <= 4){
                neighbors.get(Direction.LEFT).flavor = ParticleFlavor.FIRE;
                neighbors.get(Direction.LEFT).lifespan = FIRE_LIFESPAN;
            }
        }
    }
}
