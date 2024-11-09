import React from 'react';
import { Link } from 'react-router-dom';
import Logo from "../assets/gadgetify-inventory-system-high-resolution-logo-transparent.png";
import HomeIcon from '@mui/icons-material/Home';
import ManageAccountsIcon from '@mui/icons-material/ManageAccounts';
import CategoryIcon from '@mui/icons-material/Category';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import TrendingUpIcon from '@mui/icons-material/TrendingUp';
import EmailIcon from '@mui/icons-material/Email';
import LogoutIcon from '@mui/icons-material/Logout';

const Sidebar = () => {
    // Pairing navItems with their corresponding icons
    const navItems = [
        { name: "Dashboard", icon: <HomeIcon />, link: "/" },
        { name: "User  Management", icon: <ManageAccountsIcon />, link: "/management" },
        { name: "Categories", icon: <CategoryIcon />, link: "/categories" },
        { name: "Products", icon: <ShoppingCartIcon />, link: "/products" },
        { name: "Sales", icon: <TrendingUpIcon />, link: "/sales" },
        { name: "Sales Report", icon: <EmailIcon />, link: "/reports" }
    ];

    return (
        <div className="fixed flex flex-col w-64 min-h-screen bg-white items-center">
            <img src={Logo} alt="Inventory-Logo" className='mt-16' />
            <ul className='mt-8'>
                {navItems.map((item) => (
                    <li key={item.name} className="flex items-center py-2 px-4 hover:bg-gray-200 rounded-lg">
                        <Link to={item.link} className="flex items-center w-full">
                            <span className="mr-2">{item.icon}</span>
                            {item.name}
                        </Link>
                    </li>
                ))}
            </ul>
            <div className="log-out fixed bottom-16">
                <h3 className="cursor-pointer flex items-center">
                    <span className='mr-2'><LogoutIcon /></span>
                    Logout
                </h3>
            </div>
        </div>
    );
}

export default Sidebar;