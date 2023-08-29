import {
  configureStore,
  combineReducers,
  getDefaultMiddleware,
} from "@reduxjs/toolkit";
import { createWrapper, HYDRATE } from "next-redux-wrapper";
import { persistReducer, persistStore } from "redux-persist";
import storage from "redux-persist/lib/storage";
import { loginIdentitySlice } from "./loginIdentitySlice";
import { useDispatch, useSelector, TypedUseSelectorHook } from "react-redux";
import { Middleware } from "redux";
import thunkMiddleware, { ThunkAction, ThunkDispatch } from "redux-thunk";

const reducers = combineReducers({
  loginIdentity: loginIdentitySlice.reducer,
});

const persistConfig = {
  key: "root",
  storage,
  whitelist: ["loginIdentity"],
};

const persistedReducer = persistReducer(persistConfig, reducers);

const makeStore = () => {
  const store = configureStore({
    reducer: persistedReducer,
    middleware: (getDefaultMiddleware) =>
      getDefaultMiddleware({ serializableCheck: false }).concat(
        thunkMiddleware
      ),
  });

  return store;
};

export type RootState = ReturnType<typeof reducers>;
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;
export type AppDispatch = ThunkDispatch<RootState, undefined, any>;
export const useAppDispatch = () => useDispatch<AppDispatch>();

export const wrapper = createWrapper(makeStore, { debug: false });
export const persistor = persistStore(makeStore());
export { makeStore };
