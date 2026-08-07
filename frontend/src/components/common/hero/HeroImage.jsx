import heroImage from "@/assets/images/turf-image.jpg";

function HeroImage() {
  return (
    <div className="flex justify-center">
      <img
        src={heroImage}
        alt="Turf"
        className="rounded-3xl shadow-2xl w-full max-w-xl"
      />
    </div>
  );
}

export default HeroImage;