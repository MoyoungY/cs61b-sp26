import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import java.awt.Color;

public class TestParticle {
    @Test
    public void testConstructor() {
        Particle fp = new Particle(ParticleFlavor.FIRE);
        assertThat(fp.flavor).isEqualTo(ParticleFlavor.FIRE);
        assertThat(fp.lifespan).isEqualTo(10);

        Particle sp = new Particle(ParticleFlavor.SAND);
        assertThat(sp.flavor).isEqualTo(ParticleFlavor.SAND);
        assertThat(sp.lifespan).isEqualTo(-1);
    }

    @Test
    public void testColor() {
        Particle emptyParticle = new Particle(ParticleFlavor.EMPTY);
        assertThat(emptyParticle.color()).isEqualTo(Color.BLACK);
        Particle sandParticle = new Particle(ParticleFlavor.SAND);
        assertThat(sandParticle.color()).isEqualTo(Color.YELLOW);
        Particle barrierParticle = new Particle(ParticleFlavor.BARRIER);
        assertThat(barrierParticle.color()).isEqualTo(Color.GRAY);
        Particle waterParticle = new Particle(ParticleFlavor.WATER);
        assertThat(waterParticle.color()).isEqualTo(Color.BLUE);
        Particle fountainParticle = new Particle(ParticleFlavor.FOUNTAIN);
        assertThat(fountainParticle.color()).isEqualTo(Color.CYAN);
        Particle plantParticle = new Particle(ParticleFlavor.PLANT);
        assertThat(plantParticle.color()).isEqualTo(new Color(0, 255, 0));
        Particle fireParticle = new Particle(ParticleFlavor.FIRE);
        assertThat(fireParticle.color()).isEqualTo(new Color(255, 0, 0));
        Particle flowerParticle = new Particle(ParticleFlavor.FLOWER);
        assertThat(flowerParticle.color()).isEqualTo(new Color(255, 141, 161));
    }

    @Test
    public void testMoveInto() {
        Particle particle_a = new Particle(ParticleFlavor.FIRE);
        particle_a.lifespan = 10;
        Particle particle_b = new Particle(ParticleFlavor.EMPTY);
        particle_b.lifespan = -1;

        particle_a.moveInto(particle_b);
        assertThat(particle_a.flavor).isEqualTo(ParticleFlavor.EMPTY);
        assertThat(particle_a.lifespan).isEqualTo(-1);

        assertThat(particle_b.flavor).isEqualTo(ParticleFlavor.FIRE);
        assertThat(particle_b.lifespan).isEqualTo(10);
    }


}
