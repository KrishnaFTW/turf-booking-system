import { useEffect, useState } from "react";
import { getSports } from "@/api/api";

function SportSelector({ selectedSport, setSelectedSport }) {
    const [sports, setSports] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => {
        loadSports();
    }, []);

    async function loadSports() {
        try {
            setLoading(true);

            const data = await getSports();

            const activeSports = data.filter((sport) => sport.active);

            setSports(activeSports);

            if (activeSports.length > 0 && !selectedSport) {
                setSelectedSport(activeSports[0].name);
            }
        } catch (err) {
            console.error(err);
            setError("Unable to load sports");
        } finally {
            setLoading(false);
        }
    }

    if (loading) {
        return (
            <div className="mt-6">
                <p className="text-gray-500">Loading sports...</p>
            </div>
        );
    }

    if (error) {
        return (
            <div className="mt-6">
                <p className="text-red-500">{error}</p>
            </div>
        );
    }

    return (
        <div className="mt-6">
            <h3 className="mb-4 text-lg font-semibold">
                Choose Sport
            </h3>

            <div className="flex flex-wrap gap-3">
                {sports.map((sport) => (
                    <button
                        key={sport.id}
                        type="button"
                        onClick={() => setSelectedSport(sport.name)}
                        className={`rounded-lg border px-5 py-3 font-medium transition ${
                            selectedSport === sport.name
                                ? "border-green-600 bg-green-600 text-white"
                                : "border-gray-300 bg-white text-gray-700 hover:border-green-500"
                        }`}
                    >
                        {sport.name}
                    </button>
                ))}
            </div>
        </div>
    );
}

export default SportSelector;