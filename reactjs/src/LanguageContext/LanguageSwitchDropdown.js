import { LanguageContext } from "./LanguageContext";
import React, { useContext } from "react";
import { Dropdown } from "primereact/dropdown";

function LanguageSwitchDropdown() {
  const value = useContext(LanguageContext);
  return (
    <LanguageContext.Consumer>
      {({ language, switchLanguage }) => (
        <Dropdown
          className="p-dropdown-label"
          onChange={switchLanguage}
          value={language}
          options={[
            { label: "English", value: "EN" },
            { label: "Türkçe", value: "TR" }
          ]}
        />
      )}
    </LanguageContext.Consumer>
  );
}

export default LanguageSwitchDropdown;
