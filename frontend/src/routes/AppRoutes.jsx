import { BrowserRouter, Routes, Route } from "react-router-dom";

import LandingPage from "../pages/customer/LandingPage";
import Login from "../pages/auth/Login";
import Register from "../pages/auth/Register";
import Home from "../pages/customer/Home";

function AppRoutes() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path="/" element={<LandingPage />} />

        <Route path="/login" element={<Login />} />

        <Route path="/register" element={<Register />} />

        <Route path="/home" element={<Home />} />

      </Routes>
    </BrowserRouter>
  );
}

export default AppRoutes;