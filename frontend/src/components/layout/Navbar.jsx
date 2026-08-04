import { Link } from "react-router-dom";

function Navbar() {
  return (
    <nav className="flex justify-between items-center px-10 py-5 shadow-md bg-white">

      <h1 className="text-2xl font-bold text-green-600">
        TurfEase
      </h1>

      <div className="flex gap-8">

        <Link to="/">Home</Link>

        <Link to="/login">Login</Link>

        <Link to="/register">Register</Link>

      </div>

    </nav>
  );
}

export default Navbar;