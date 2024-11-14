import React from 'react';
import { Link } from 'react-router-dom';
import Logo from "../assets/inventory-logo.svg";
import HomeIcon from '@mui/icons-material/Home';
import ManageAccountsIcon from '@mui/icons-material/ManageAccounts';
import CategoryIcon from '@mui/icons-material/Category';
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import TrendingUpIcon from '@mui/icons-material/TrendingUp';
import EmailIcon from '@mui/icons-material/Email';
import LogoutIcon from '@mui/icons-material/Logout';
import { Settings } from '@mui/icons-material';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import ManagerSidebar from './sidebars/ManagerSidebar';
import SalesSidebar from './sidebars/SalesSidebar';


// Role-Based Access Control (Optional)
const SidebarWithRoleControl = () => {
    const { user } = useAuth();
  
    const renderSidebarBasedOnRole = () => {
      switch(user.role) {
        case 'Admin':
          return <Sidebar/>;
        case 'Sales Staff':
          return <SalesSidebar />;
        case 'Manager':
          return <ManagerSidebar />;
      }
    };
  
    return renderSidebarBasedOnRole();
  };

const Sidebar = () => {
    
    
    const navItems = [
        { name: "Dashboard", icon: <HomeIcon />, link: "/dashboard" },
        { name: "Inventory", icon: <CategoryIcon />, link: "/inventory" },
        { name: "Sales", icon: <TrendingUpIcon />, link: "/sales" },
        { name: "Service", icon: <ShoppingCartIcon />, link: "/service" },
        { name: "Sales Report", icon: <EmailIcon />, link: "/reports" },
        { name: "Store Management", icon: <ManageAccountsIcon />, link: "/management" }
    ];

    const profileItems = [
        { name: "Profile", icon: <AccountCircleIcon />, link: "/profile" },
        { name: "Settings", icon: <Settings />, link: "/settings" },
        { name: "Logout", icon: <LogoutIcon />, link: "/" },
    ]

    return (
        <div className="fixed flex flex-col w-64 min-h-screen bg-white items-center">
            <div className="flex flex-row w-full justify-center items-center mt-16">
                <img src={Logo} alt="header-logo" className="w-1/4" />
                <h3 className="text-[black]  items-center text-2xl">InventoryHUB</h3>
            </div>
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
                <ul className='mt-8'>
                    {profileItems.map((item) => (
                        <li key={item.name} className="flex items-start py-2 px-4 hover:bg-gray-200 rounded-lg">
                            <Link to={item.link} className="flex items-center w-full">
                                <span className="mr-2">{item.icon}</span>
                                {item.name}
                            </Link>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default Sidebar;