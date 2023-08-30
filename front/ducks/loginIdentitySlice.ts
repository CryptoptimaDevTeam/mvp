import { createSlice } from "@reduxjs/toolkit";
import { removeCookie } from "../module/cookies";

interface Idata {
  userId: number;
  username: string;
}

interface loginIdentity {
  isLogin: boolean;
  userId: number | null;
  username: string | null;
}
const initialState: loginIdentity = {
  isLogin: false,
  userId: null,
  username: null,
};

export const loginIdentitySlice = createSlice({
  name: "loginIdentitySlice",
  initialState,
  reducers: {
    initLoginIdentity: (state: loginIdentity): void => {
      state.isLogin = false;
      state.userId = null;
      state.username = null;
      removeCookie("refreshJwtToken");
    },
    oauthLogin: (state, action): void => {
      state.isLogin = true;
      state.userId = action.payload.userId;
      state.username = action.payload.username;
    },
  },
});
export const { initLoginIdentity, oauthLogin } = loginIdentitySlice.actions;
