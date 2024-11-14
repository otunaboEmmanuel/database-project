import React from 'react'
import SidebarWithRoleControl from '../components/SidebarWithRoleControl'; // Import the SidebarWithRoleControl
import Container from '../components/Container';
import { AuthProvider } from '../components/Auth';
const Inventory = () => {
    return (
        <AuthProvider>
            <div className="home-page flex flex-row w-full min-h-screen">
                <SidebarWithRoleControl /> {/* Use SidebarWithRoleControl instead of Sidebar */}
                <div className="ml-64 w-full bg-[#f4f4f4]">
                    Inventory
                </div>
            </div>
        </AuthProvider>
    )
}

export default Inventory