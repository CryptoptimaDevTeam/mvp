import { Header } from "../components/blocks/header";
import { Footer } from "../components/blocks/footer";

export default function Home() {
  return (
    <div>
      <Header />
      <div className="h-[500px]">안녕</div>
      <div className="h-[500px]">안녕</div>
      <div className="h-[500px]">안녕</div>
      <div className="h-[500px]">안녕</div>
      <Footer />
    </div>
  );
}
