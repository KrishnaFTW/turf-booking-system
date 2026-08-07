import Container from "@/components/layout/Container";

import HeroImage from "./HeroImage";
import HeroButtons from "./HeroButtons";
import HeroContent from "./HeroContent";
import HeroStats from "./HeroStats";
import heroImage from "@/assets/images/turf-image.jpg";

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

    <div className="relative z-10 grid lg:grid-cols-2 gap-16 items-center">

    <div>

        <HeroContent />

        <HeroButtons />

        <HeroStats />

    </div>

    <HeroImage />

</div>

</Container>

    </section>
  );
}

export default HeroSection;