import React from 'react'
import { AuthProvider } from '../components/Auth'
import SidebarWithRoleControl from '../components/SidebarWithRoleControl'

const Settings = () => {
  return (
    <>
            <AuthProvider>
                <div className="home-page flex flex-row w-full min-h-screen">
                    <SidebarWithRoleControl /> {/* Use SidebarWithRoleControl instead of Sidebar */}
                    <div className="ml-64 w-full bg-[#f4f4f4]">
                       Settings
                    </div>
                </div>
            </AuthProvider>
        </>
  )
}

export default Settings