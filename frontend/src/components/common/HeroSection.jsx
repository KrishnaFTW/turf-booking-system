import Container from "../layout/Container";
import HeroButtons from "./HeroButtons";
import HeroContent from "./HeroContent";
import HeroStats from "./HeroStats";
import heroImage from "../../assets/images/turf-image.jpg";

function HeroSection() {
  return (
    <section
    className="relative min-h-[90vh] flex items-center bg-cover bg-center"
    style={{
        backgroundImage: `url(${heroImage})`,
    }}
>
  <div className="absolute inset-0 bg-black/60"></div>

      <Container>

    <div className="relative z-10">

        <HeroContent />

        <HeroButtons />

        <HeroStats />

    </div>

</Container>

    </section>
  );
}

export default HeroSection;