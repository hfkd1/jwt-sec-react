import React, { Component, useContext } from "react";
import { LanguageProvider, LanguageContext } from "../LanguageContext";
export const Dashboard = () => {
  //const [languages, setLanguages] = useContext(LanguageContext);
  const [languages, setLanguages] = useContext(LanguageContext);

  return (
    <div>
      {languages.map(str =>
        str.id === "appmenu_tr" ? <h1>{str.txt}</h1> : ""
      )}
    </div>
  );
};
