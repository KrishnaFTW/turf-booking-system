import MainLayout from "@/components/layout/MainLayout";

import HeroSection from "@/components/common/hero/HeroSection";

import SportsSection from "../../components/common/SportsSection";

import FeaturesSection from "../../components/common/FeaturesSection";

function LandingPage() {
  return (
    <MainLayout>

      <HeroSection />

      <SportsSection />

      <FeaturesSection />

    </MainLayout>
  );
}

export default LandingPage;