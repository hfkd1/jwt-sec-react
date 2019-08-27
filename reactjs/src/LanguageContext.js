import React, { useState, createContext } from "react";

export const LanguageContext = createContext();
export const LanguageProvider = props => {
  const [languages, setLanguages] = useState([
    { id: "dashboard_en", text1: "Trial" },
    { id: "dashboard_tr", text1: "deneme" },
    { id: "appmenu_en", txt: "Dashboard" },
    { id: "appmenu_tr", txt: "Panel" }
  ]);

  return (
    <LanguageContext.Provider value={[languages, setLanguages]}>
      {props.children}
    </LanguageContext.Provider>
  );
};
