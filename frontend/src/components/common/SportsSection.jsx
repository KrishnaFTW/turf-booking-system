function SportsSection() {
  return (
    <section className="py-16">

      <h2 className="text-4xl text-center font-bold">
        Sports Available
      </h2>

      <div className="flex justify-center gap-10 mt-10">

        <div className="border rounded-xl p-8 shadow-md w-64 text-center">
          <h3 className="text-2xl font-bold">
            🏏 Cricket
          </h3>
        </div>

        <div className="border rounded-xl p-8 shadow-md w-64 text-center">
          <h3 className="text-2xl font-bold">
            ⚽ Football
          </h3>
        </div>

      </div>

    </section>
  );
}

export default SportsSection;