import { Link } from "react-router-dom";

import Button from "../ui/Button";
import Container from "./Container";

function Navbar() {
  return (
    <nav className="bg-white shadow-sm sticky top-0 z-50">
      <Container>
        <div className="flex justify-between items-center h-20">
          <h1 className="text-3xl font-bold text-green-600">
            TurfEase
          </h1>

          <div className="flex items-center gap-8">
            <Link to="/">Home</Link>
            <Link to="/">Sports</Link>
            <Link to="/">Pricing</Link>
            <Link to="/">Contact</Link>

            <Link to="/login">
              Login
            </Link>

            <Link to="/register">
              <Button>

                Book Now

              </Button>
            </Link>
          </div>
        </div>
      </Container>
    </nav>
  );
}

export default Navbar;