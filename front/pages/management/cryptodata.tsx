import type { ReactElement } from "react";
import ManageLayout from "../../layout/manageLayout";

const ManageCryptoData = () => {
  return <div></div>;
};

export default ManageCryptoData;

ManageCryptoData.getLayout = function getLayout(page: ReactElement) {
  return <ManageLayout>{page}</ManageLayout>;
};
