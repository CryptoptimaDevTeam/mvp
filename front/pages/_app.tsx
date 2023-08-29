import "../styles/globals.css";
import type { NextPage } from "next";
import type { AppProps } from "next/app";
import type { ReactElement, ReactNode } from "react";
import DefaultLayout from "../layout/defaultLayout";

export type NextPageWithLayout<P = {}, IP = P> = NextPage<P, IP> & {
  getLayout?: (page: ReactElement) => ReactNode;
};
type AppPropsWithLayout = AppProps & {
  Component: NextPageWithLayout;
};

export default function App({ Component, pageProps }: AppPropsWithLayout) {
  const getLayout = Component.getLayout || ((page) => page);

  return (
    <>
      {Component.getLayout ? (
        getLayout(<Component {...pageProps} />)
      ) : (
        <DefaultLayout>{getLayout(<Component {...pageProps} />)}</DefaultLayout>
      )}
    </>
  );
}
