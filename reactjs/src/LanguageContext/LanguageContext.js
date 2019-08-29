import React, { useState, createContext } from "react";

export const languages = {
  english: "EN",
  turkish: "TR",

  //strings that will be used should be initialized here
  dashboard_en: {
    text1: "trial"
  },
  dashboard_tr: {
    text1: "deneme"
  },
  topbar_en: {
    searchPlaceholder: "Search"
  },
  topbar_tr: {
    searchPlaceholder: "Ara"
  },
  profile_en: {
    account: "Account",
    notifications: "Notifications",
    logout: "Logout"
  },
  profile_tr: {
    account: "Hesap",
    notifications: "Bildirimler",
    logout: "Çıkış"
  }
};

export const LanguageContext = React.createContext({
  language: languages.english, // default value
  dashboard: languages.dashboard_en,
  topbar: languages.topbar_en,
  switchLanguage: () => {}
});
