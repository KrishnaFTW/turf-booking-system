import Navbar from "../../components/layout/Navbar";
import HeroSection from "../../components/common/HeroSection";
import SportsSection from "../../components/common/SportsSection";
import FeaturesSection from "../../components/common/FeaturesSection";
import Footer from "../../components/layout/Footer";

function LandingPage() {
  return (
    <>
      <Navbar />

      <HeroSection />

      <SportsSection />

      <FeaturesSection />

      <Footer />
    </>
  );
}

export default LandingPage;