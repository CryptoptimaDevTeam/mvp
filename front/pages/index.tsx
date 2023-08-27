import { ReactElement } from 'react';
import NoLayout from '../layout/noLayout';
import Main from './main';
import PreResister from './pre-resister';

export default function Home() {
  return (
    <div>
      <PreResister />
      {/* <Main /> */}
    </div>
  );
}

Home.getLayout = function getLayout(page: ReactElement) {
  return <NoLayout>{page}</NoLayout>;
};
