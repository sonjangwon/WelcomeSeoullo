package com.example.jangwon.welcomeseoullo.PublicData;

import com.example.jangwon.welcomeseoullo.LoadingDialog;

import java.util.ArrayList;

public class ManagePublicData {

    private static ManagePublicData managePublicData;

    PublicToiletVO publicToiletVO;
    PublicParkingLotVO publicParkingLotVO;
    PublicParkVO publicParkVO;
    TraditionalMarketVO traditionalMarketVO;

    private ArrayList<PublicToiletVO> publicToiletVOArrayList;
    private ArrayList<PublicParkingLotVO> publicParkingLotVOArrayList;
    private ArrayList<PublicParkVO> publicParkVOArrayList;
    private ArrayList<TraditionalMarketVO> traditionalMarketVOArrayList;

    LoadingDialog loadingDialog;

    String tag_name = null;
    boolean bSet = false;

    public static ManagePublicData getInstance() {
        if(managePublicData == null){
            managePublicData = new ManagePublicData();
        }
        return managePublicData;
    }

    private ManagePublicData() {
        publicToiletVO = new PublicToiletVO();
        publicParkingLotVO = new PublicParkingLotVO();
        publicParkVO = new PublicParkVO();
        traditionalMarketVO = new TraditionalMarketVO();

        publicToiletVOArrayList = new ArrayList<PublicToiletVO>(120);
        publicParkingLotVOArrayList = new ArrayList<PublicParkingLotVO>(20);
        publicParkVOArrayList = new ArrayList<PublicParkVO>(10);
        traditionalMarketVOArrayList = new ArrayList<TraditionalMarketVO>(20);

        addToiletVO();
        addParkingLotVO();
        addParkVO();
        addTraditionalMarketVO();
    }

    public ArrayList<PublicToiletVO> getPublicToiletVOArrayList(){
        return publicToiletVOArrayList;
    }
    public void setPublicToiletVOArrayList(ArrayList<PublicToiletVO> publicToiletVOArrayList){
        this.publicToiletVOArrayList = publicToiletVOArrayList;
    }

    public ArrayList<PublicParkingLotVO> getPublicParkingLotVOArrayList(){
        return publicParkingLotVOArrayList;
    }
    public void setPublicParkingLotVOArrayList(ArrayList<PublicParkingLotVO> publicParkingLotVOArrayList){
        this.publicParkingLotVOArrayList = publicParkingLotVOArrayList;
    }

    public ArrayList<PublicParkVO> getPublicParkVOArrayList(){
        return publicParkVOArrayList;
    }
    public void setPublicParkVOArrayList(ArrayList<PublicParkVO> publicParkVOArrayList){
        this.publicParkVOArrayList = publicParkVOArrayList;
    }

    public ArrayList<TraditionalMarketVO> getTraditionalMarketVOArrayList(){
        return traditionalMarketVOArrayList;
    }
    public void setTraditionalMarketVOArrayList(ArrayList<TraditionalMarketVO> traditionalMarketVOArrayList){
        this.traditionalMarketVOArrayList = traditionalMarketVOArrayList;
    }

    public boolean calculateCoordinates(String latitude, String longitude){
        if(37.535936 < Double.valueOf(latitude) && Double.valueOf(latitude) < 37.571883 &&
                126.949482 < Double.valueOf(longitude) && Double.valueOf(longitude) < 126.995575){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean calculatePublicToiletCoordinates(String latitude, String longitude){
        if(37.546160 < Double.valueOf(latitude) && Double.valueOf(latitude) < 37.564250 &&
                126.960205 < Double.valueOf(longitude) && Double.valueOf(longitude) < 126.983023){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean checkDuplication(String ParkingLotName){
        for(int i=0;i<publicParkingLotVOArrayList.size();i++){
            if(publicParkingLotVOArrayList.get(i).getParkingLotName().equals(ParkingLotName)){
                return false;
            }
        }
        return true;
    }

    public void addToiletVO() {
        publicToiletVOArrayList.add(new PublicToiletVO("동화빌딩", String.valueOf(37.562583518437265),String.valueOf(126.97297540786496)));
        publicToiletVOArrayList.add(new PublicToiletVO("삼구상사(주)", String.valueOf(37.563853976106095),String.valueOf(126.98080545627715)));
        publicToiletVOArrayList.add(new PublicToiletVO("케이티중앙지사", String.valueOf(37.56186876289762),String.valueOf(126.98265825452533)));
        publicToiletVOArrayList.add(new PublicToiletVO("만리공중화장실", String.valueOf(37.55192071382412),String.valueOf(126.96224920473297)));
        publicToiletVOArrayList.add(new PublicToiletVO("삼선빌딩", String.valueOf(37.5573935460615),String.valueOf(126.97710219735032)));
        publicToiletVOArrayList.add(new PublicToiletVO("대한생명빌딩", String.valueOf(37.557605295316684),String.valueOf(126.97552785011635)));
        publicToiletVOArrayList.add(new PublicToiletVO("서소문근린공원공중화장실", String.valueOf(37.56056793090174),String.valueOf(126.9693746542471)));
        publicToiletVOArrayList.add(new PublicToiletVO("후암4공중화장실", String.valueOf(37.550245360179346),String.valueOf(126.97622968782944)));
        publicToiletVOArrayList.add(new PublicToiletVO("동자공원공중화장실", String.valueOf(37.55012128865461),String.valueOf(126.9738684862958)));
        publicToiletVOArrayList.add(new PublicToiletVO("효창공원놀이터공중화장실", String.valueOf(37.54663139474883),String.valueOf(126.9609369965899)));
        publicToiletVOArrayList.add(new PublicToiletVO("새나라공원공중화장실", String.valueOf(37.54789932671594),String.valueOf(126.97657136350888)));
        publicToiletVOArrayList.add(new PublicToiletVO("소월길주유소화장실", String.valueOf(37.55444420522051),String.valueOf(126.97770399372452)));
        publicToiletVOArrayList.add(new PublicToiletVO("서울중앙우체국", String.valueOf(37.561484398149084),String.valueOf(126.98210901183968)));
        publicToiletVOArrayList.add(new PublicToiletVO("갈월동주유소화장실", String.valueOf(37.54698162880048),String.valueOf(126.97218807114557)));
        publicToiletVOArrayList.add(new PublicToiletVO("역전주유소화장실", String.valueOf(37.55699259436758),String.valueOf(126.97453003608209)));
        publicToiletVOArrayList.add(new PublicToiletVO("만리주유소화장실", String.valueOf(37.55268401534898),String.valueOf(126.96356308068766)));
        publicToiletVOArrayList.add(new PublicToiletVO("중구파출소", String.valueOf(37.557658830505424),String.valueOf(126.98210807609549)));
        publicToiletVOArrayList.add(new PublicToiletVO("중림파출소", String.valueOf(37.55787272444392),String.valueOf(126.96712170968073)));
        publicToiletVOArrayList.add(new PublicToiletVO("퇴계로5가우체국", String.valueOf(37.55756231290616),String.valueOf(126.97141047462931)));
        publicToiletVOArrayList.add(new PublicToiletVO("남대문파출소", String.valueOf(37.559020274035774),String.valueOf(126.97638645668164)));
        publicToiletVOArrayList.add(new PublicToiletVO("남대문2가파출소", String.valueOf(37.55713604052238),String.valueOf(126.97612389125852)));
        publicToiletVOArrayList.add(new PublicToiletVO("서울역지구대", String.valueOf(37.556792353593345),String.valueOf(126.97170089503753)));
        publicToiletVOArrayList.add(new PublicToiletVO("일중기업B동", String.valueOf(37.56318122351443),String.valueOf(126.97161591371776)));
        publicToiletVOArrayList.add(new PublicToiletVO("삼성본관무인자동화장실", String.valueOf(37.56302612709622),String.valueOf(126.97609954125983)));
        publicToiletVOArrayList.add(new PublicToiletVO("남대문시장입구1무인자동화장실", String.valueOf(37.55896973798338),String.valueOf(126.97616588485123)));
        publicToiletVOArrayList.add(new PublicToiletVO("남대문시장입구2무인자동화장실", String.valueOf(37.55896973798338),String.valueOf(126.97616588485123)));
        publicToiletVOArrayList.add(new PublicToiletVO("숭례문무인자동화장실", String.valueOf(37.559256686597756),String.valueOf(126.97518317814465)));
        publicToiletVOArrayList.add(new PublicToiletVO("올리브타워", String.valueOf(37.56142923620549),String.valueOf(126.97296640801912)));
        publicToiletVOArrayList.add(new PublicToiletVO("산경물산주식회", String.valueOf(37.55782382569501),String.valueOf(126.96869058637004)));
        publicToiletVOArrayList.add(new PublicToiletVO("연세세브란스빌딩", String.valueOf(37.55725343432517),String.valueOf(126.97354111388195)));
        publicToiletVOArrayList.add(new PublicToiletVO("연세봉래빌딩", String.valueOf(37.559466499150744),String.valueOf(126.97283187805559)));
        publicToiletVOArrayList.add(new PublicToiletVO("씨티파크", String.valueOf(37.557956596657746),String.valueOf(126.97554760261518)));
        publicToiletVOArrayList.add(new PublicToiletVO("삼화빌딩", String.valueOf(37.56297143961496),String.valueOf(126.97864478214312)));
        publicToiletVOArrayList.add(new PublicToiletVO("서울상공회의소", String.valueOf(37.56080803034071),String.valueOf(126.97381767631019)));
        publicToiletVOArrayList.add(new PublicToiletVO("아이엔지센터", String.valueOf(37.56083381763615),String.valueOf(126.97246801498979)));
        publicToiletVOArrayList.add(new PublicToiletVO("아일빌딩", String.valueOf(37.557177164456505),String.valueOf(126.98265799640689)));
        publicToiletVOArrayList.add(new PublicToiletVO("외환은행빌딩", String.valueOf(37.562730914756386),String.valueOf(126.97362398076037)));
        publicToiletVOArrayList.add(new PublicToiletVO("유원빌딩", String.valueOf(37.56273378185858),String.valueOf(126.97389015716956)));
        publicToiletVOArrayList.add(new PublicToiletVO("중림동공중화장실", String.valueOf(37.556318638433275),String.valueOf(126.96401592915659)));
        publicToiletVOArrayList.add(new PublicToiletVO("충정로역", String.valueOf(37.560439412290144),String.valueOf(126.96295764107946)));
        publicToiletVOArrayList.add(new PublicToiletVO("청파공원공중화장실", String.valueOf(37.55354897095527),String.valueOf(126.96827867352042)));
        publicToiletVOArrayList.add(new PublicToiletVO("서울역(4)", String.valueOf(37.554027322098904),String.valueOf(126.97284236687933)));
        publicToiletVOArrayList.add(new PublicToiletVO("회현역", String.valueOf(37.558661733793244),String.valueOf(126.97829646810474)));
        publicToiletVOArrayList.add(new PublicToiletVO("시청역(2)", String.valueOf(37.56314801496914),String.valueOf(126.97435624140974)));
        publicToiletVOArrayList.add(new PublicToiletVO("아웃백시청점개방화장실", String.valueOf(37.563632145496804),String.valueOf(126.97718447253689)));
        publicToiletVOArrayList.add(new PublicToiletVO("GS빌딩", String.valueOf(37.55633943520506),String.valueOf(126.97413335995142)));
        publicToiletVOArrayList.add(new PublicToiletVO("(주)신세계별관", String.valueOf(37.56100078364559),String.valueOf(126.98093619661581)));
        publicToiletVOArrayList.add(new PublicToiletVO("경찰청무인자동화장실", String.valueOf(37.56392216305422),String.valueOf(126.96866956238034)));
        publicToiletVOArrayList.add(new PublicToiletVO("신세계백화점", String.valueOf(37.56051218912744),String.valueOf(126.98098123567927)));
        publicToiletVOArrayList.add(new PublicToiletVO("(주)옥션", String.valueOf(37.55921140427813),String.valueOf(126.98087520009726)));
        publicToiletVOArrayList.add(new PublicToiletVO("동화주차빌딩", String.valueOf(37.562282888553774),String.valueOf(126.97333399945985)));
        publicToiletVOArrayList.add(new PublicToiletVO("(주)삼원이엔에스", String.valueOf(37.563743495386376),String.valueOf(126.97889740096709)));
        publicToiletVOArrayList.add(new PublicToiletVO("고려통상주식회사", String.valueOf(37.56098578527828),String.valueOf(126.98262120917192)));
        publicToiletVOArrayList.add(new PublicToiletVO("충정로역", String.valueOf(37.55964352986086),String.valueOf(126.96427220645973)));
        publicToiletVOArrayList.add(new PublicToiletVO("대한통운빌딩", String.valueOf(37.561983400591814),String.valueOf(126.97283252370957)));
        publicToiletVOArrayList.add(new PublicToiletVO("(주)신흥", String.valueOf(37.55972401993177),String.valueOf(126.96880822770476)));
        publicToiletVOArrayList.add(new PublicToiletVO("대한항공빌딩", String.valueOf(37.5636477245611),String.valueOf(126.97409380908745)));
        publicToiletVOArrayList.add(new PublicToiletVO("제분빌딩", String.valueOf(37.55886771576421),String.valueOf(126.97534893351617)));
        publicToiletVOArrayList.add(new PublicToiletVO("한화역사(주)", String.valueOf(37.555929234347566),String.valueOf(126.97203309249757)));
        publicToiletVOArrayList.add(new PublicToiletVO("후암동주민센터", String.valueOf(37.54871118553836),String.valueOf(126.9781718243156)));
        publicToiletVOArrayList.add(new PublicToiletVO("후암119안전센터", String.valueOf(37.54902098554847),String.valueOf(126.97768836793465)));
        publicToiletVOArrayList.add(new PublicToiletVO("서부수도사업소", String.valueOf(37.55704023887256),String.valueOf(126.96023018309648)));
        publicToiletVOArrayList.add(new PublicToiletVO("충정로지구대", String.valueOf(37.562263464209146),String.valueOf(126.96385698500646)));
        publicToiletVOArrayList.add(new PublicToiletVO("상수도사업본부", String.valueOf(37.56193612193632),String.valueOf(126.9667789903919)));
        publicToiletVOArrayList.add(new PublicToiletVO("회현동주민센터", String.valueOf(37.55728537986629),String.valueOf(126.97937463120347)));
        publicToiletVOArrayList.add(new PublicToiletVO("대원강업", String.valueOf(37.55848287456724),String.valueOf(126.9727211386329)));
        publicToiletVOArrayList.add(new PublicToiletVO("삼성생명보험(주)", String.valueOf(37.56049845985882),String.valueOf(126.97292501656757)));
        publicToiletVOArrayList.add(new PublicToiletVO("흥국생명보험(주)", String.valueOf(37.56125845863579),String.valueOf(126.97738956459042)));
        publicToiletVOArrayList.add(new PublicToiletVO("충정타워개방화장실", String.valueOf(37.55995850435825),String.valueOf(126.9642281302011)));
        publicToiletVOArrayList.add(new PublicToiletVO("리젠트화재개방화장실", String.valueOf(37.562042111291426),String.valueOf(126.96443491686807)));
        publicToiletVOArrayList.add(new PublicToiletVO("대우재단빌딩", String.valueOf(37.55662758911037),String.valueOf(126.97519584237128)));
        publicToiletVOArrayList.add(new PublicToiletVO("SC제일은행", String.valueOf(37.56109795744918),String.valueOf(126.98037964140461)));
        publicToiletVOArrayList.add(new PublicToiletVO("STX남산타워", String.valueOf(37.554053044265075),String.valueOf(126.97482455349314)));
        publicToiletVOArrayList.add(new PublicToiletVO("기독교대한감리회유지재단", String.valueOf(37.56076107018461),String.valueOf(126.9792311642354)));
        publicToiletVOArrayList.add(new PublicToiletVO("남정개발주식회사", String.valueOf(37.559810046939646),String.valueOf(126.97816147670774)));
        publicToiletVOArrayList.add(new PublicToiletVO("종근당빌딩개방화장실", String.valueOf(37.55971195342335),String.valueOf(126.96329681161328)));
        publicToiletVOArrayList.add(new PublicToiletVO("피어리스개방화장실", String.valueOf(37.56286000397748),String.valueOf(126.96508611549574)));
        publicToiletVOArrayList.add(new PublicToiletVO("삼창빌딩개방화장실", String.valueOf(37.56051397218105),String.valueOf(126.96263245235677)));
        publicToiletVOArrayList.add(new PublicToiletVO("인송빌딩개방화장실", String.valueOf(37.55832585705475),String.valueOf(126.97854022837545)));
        publicToiletVOArrayList.add(new PublicToiletVO("숭례문수입상가운영회개방화장실", String.valueOf(37.559418333892694),String.valueOf(126.97635661053361)));
        publicToiletVOArrayList.add(new PublicToiletVO("우리빌딩", String.valueOf(37.559423572109516),String.valueOf(126.97343077729596)));
        publicToiletVOArrayList.add(new PublicToiletVO("알파유통(주)개방화장실", String.valueOf(37.56014329735304),String.valueOf(126.97659667533809)));
        publicToiletVOArrayList.add(new PublicToiletVO("영원프라자", String.valueOf(37.55337578286058),String.valueOf(126.96399074063493)));
        publicToiletVOArrayList.add(new PublicToiletVO("힐튼호텔", String.valueOf(37.55568837644386),String.valueOf(126.97541974801071)));
        publicToiletVOArrayList.add(new PublicToiletVO("롯데마트개방화장실", String.valueOf(37.558487267262095),String.valueOf(126.96966692947241)));
        publicToiletVOArrayList.add(new PublicToiletVO("영화빌딩", String.valueOf(37.5606110702774),String.valueOf(126.97725184036248)));
        publicToiletVOArrayList.add(new PublicToiletVO("명동", String.valueOf(37.56356318572479),String.valueOf(126.98178054124514)));
        publicToiletVOArrayList.add(new PublicToiletVO("중림동주민센터", String.valueOf(37.554808540585846),String.valueOf(126.96452103825422)));
        publicToiletVOArrayList.add(new PublicToiletVO("청파주유소화장실", String.valueOf(37.54752596393396),String.valueOf(126.96992062619947)));
        publicToiletVOArrayList.add(new PublicToiletVO("소공동주민센터", String.valueOf(37.56240038428345),String.valueOf(126.97703481745715)));
        publicToiletVOArrayList.add(new PublicToiletVO("정안빌딩", String.valueOf(37.56288647622255),String.valueOf(126.97191219579523)));
        publicToiletVOArrayList.add(new PublicToiletVO("서울시티타워", String.valueOf(37.55439960357872),String.valueOf(126.97413927110487)));
        publicToiletVOArrayList.add(new PublicToiletVO("정석힉원", String.valueOf(37.55817231884166),String.valueOf(126.97217127920874)));
        publicToiletVOArrayList.add(new PublicToiletVO("한국YMCA", String.valueOf(37.56319654039625),String.valueOf(126.97860738999451)));
        publicToiletVOArrayList.add(new PublicToiletVO("(주)부영", String.valueOf(37.56177072647245),String.valueOf(126.97385156340287)));
        publicToiletVOArrayList.add(new PublicToiletVO("한전산업개발(주)", String.valueOf(37.563482039307985),String.valueOf(126.97353249241306)));
        publicToiletVOArrayList.add(new PublicToiletVO("한화건설", String.valueOf(37.56371441830512),String.valueOf(126.9749562177026)));
        publicToiletVOArrayList.add(new PublicToiletVO("디에스디엘주식회사", String.valueOf(37.56148662201884),String.valueOf(126.97650442521946)));
        publicToiletVOArrayList.add(new PublicToiletVO("맵스자산운영(주)", String.valueOf(37.5637124138651),String.valueOf(126.96922706430358)));
        publicToiletVOArrayList.add(new PublicToiletVO("(주)배재학당 동관", String.valueOf(37.56367080456106),String.valueOf(126.97233900004741)));
        publicToiletVOArrayList.add(new PublicToiletVO("메사", String.valueOf(37.560453272829186),String.valueOf(126.97967787175767)));
        publicToiletVOArrayList.add(new PublicToiletVO("태평빌딩", String.valueOf(37.56281946159966),String.valueOf(126.97702485241741)));
        publicToiletVOArrayList.add(new PublicToiletVO("한화빌딩개방화장실", String.valueOf(37.56422301856528),String.valueOf(126.97863901220623)));
        publicToiletVOArrayList.add(new PublicToiletVO("(주)서울렉스호텔", String.valueOf(37.558740077097404),String.valueOf(126.98093472162537)));
        publicToiletVOArrayList.add(new PublicToiletVO("서울역", String.valueOf(37.55577850251268),String.valueOf(126.97049701580457)));
        publicToiletVOArrayList.add(new PublicToiletVO("현대해상화재보험(주)", String.valueOf(37.56233583822648),String.valueOf(126.98225741086138)));
        publicToiletVOArrayList.add(new PublicToiletVO("홍덕빌딩", String.valueOf(37.55754549414622),String.valueOf(126.9811707355787)));
        publicToiletVOArrayList.add(new PublicToiletVO("HSBC빌딩", String.valueOf(37.56017002696947),String.valueOf(126.97305705679142)));
        publicToiletVOArrayList.add(new PublicToiletVO("MIES빌딩", String.valueOf(37.56409749681692),String.valueOf(126.97592766195088)));
        publicToiletVOArrayList.add(new PublicToiletVO("(주)삼부토건", String.valueOf(37.5584578545086),String.valueOf(126.97899920209525)));
        publicToiletVOArrayList.add(new PublicToiletVO("배재빌딩", String.valueOf(37.56298843173425),String.valueOf(126.97265357783282)));
        publicToiletVOArrayList.add(new PublicToiletVO("신남문빌딩", String.valueOf(37.55905228840269),String.valueOf(126.97487430799008)));
        publicToiletVOArrayList.add(new PublicToiletVO("서계주유소화장실", String.valueOf(37.552372202511265),String.valueOf(126.96895701986536)));
        publicToiletVOArrayList.add(new PublicToiletVO("동자동주유소화장실", String.valueOf(37.55015115206399),String.valueOf(126.97231296910222)));
        publicToiletVOArrayList.add(new PublicToiletVO("서남주유소화장실", String.valueOf(37.55838941592916),String.valueOf(126.97214199569845)));
    }

    public void addParkingLotVO() {
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("세운상가밑(구)","노상 주차장",String.valueOf(37.56941418),String.valueOf(126.99549249)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("세일철강~LG전자연구소(구)","노상 주차장",String.valueOf(37.55559696),String.valueOf(126.96497303)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("조양화학(구)","노상 주차장",String.valueOf(37.56954962),String.valueOf(126.98364791)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("종각(구)","노상 주차장",String.valueOf(37.5695603),String.valueOf(126.98384962)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("공덕1-1 공영주차장(구)","노외 주차장",String.valueOf(37.54738044),String.valueOf(126.9609133)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("광화문빌딩(구)","노상 주차장",String.valueOf(37.57159097),String.valueOf(126.97384239)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("한미빌딩옆(구)","노상 주차장",String.valueOf(37.57185042),String.valueOf(126.98449538)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("남대문시장2번게이트앞 이륜자동차주차장(시)","노상 주차장",String.valueOf(37.56081922),String.valueOf(126.97756119)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("남대문시장3번게이트 이륜자동차주차장(시)","노상 주차장",String.valueOf(37.56098007),String.valueOf(126.97832641)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("남대문시장알파문구앞 이륜자동차주차장(시)","노상 주차장",String.valueOf(37.56033077),String.valueOf(126.97629081)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("남대문시장연세상가앞 이륜자동차주차장(시)","노상 주차장",String.valueOf(37.55760957),String.valueOf(126.97647624)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("남대문시장자유상가앞 이륜차주차장(시)","노상 주차장",String.valueOf(37.56121387),String.valueOf(126.97961264)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("미근동노외(시)","노외 주차장",String.valueOf(37.56225368),String.valueOf(126.9683494)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("남산동 공영주차장(구)","노외 주차장",String.valueOf(37.55923691),String.valueOf(126.98562404)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("남산소파길 노상 공영주차장(구)","노상 주차장",String.valueOf(37.55669584),String.valueOf(126.98540056)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("뉴국제호텔 앞 노상 공영주차장(구)","노상 주차장",String.valueOf(37.56819564),String.valueOf(126.97824564)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("다동공원 제4부지 공영주차장(구)","노외 주차장",String.valueOf(37.56823533),String.valueOf(126.98025108)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("다동쉼터 노상공영주차장(구)","노상 주차장",String.valueOf(37.56794477),String.valueOf(126.98004106)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("영국대사관 옆 노상 공영주차장(구)","노상 주차장",String.valueOf(37.56657538),String.valueOf(126.97602679)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("청소년회관 뒤 노상 공영주차장(구)","노상 주차장",String.valueOf(37.56750928),String.valueOf(126.99033122)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("청파동1마을공원공영주차장(구)","노외 주차장",String.valueOf(37.54294115),String.valueOf(126.96847142)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("청파동청사(구)","노외 주차장",String.valueOf(37.54549939),String.valueOf(126.96991775)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("초동 공영주차장(구)","노외 주차장",String.valueOf(37.56416856),String.valueOf(126.99213555)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("충현동 제1거주자우선주차장(구)","노외 주차장",String.valueOf(37.56518547),String.valueOf(126.96460687)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("코오롱빌딩옆 노상공영주차장(구)","노상 주차장",String.valueOf(37.56727633),String.valueOf(126.97824353)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("탑골공원(구)","노상 주차장",String.valueOf(37.57169781),String.valueOf(126.98777728)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("대림상가노상공영주차장(구)","노상 주차장",String.valueOf(37.5680469),String.valueOf(126.99518415)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("북아현 가구단지(구)","노외 주차장",String.valueOf(37.55829286),String.valueOf(126.96035541)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("한국관광공사옆 노상공영주차장(구)","노상 주차장",String.valueOf(37.56836964),String.valueOf(126.9820308)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("삼풍상가 노상 공영주차장(구)","노상 주차장",String.valueOf(37.56519697),String.valueOf(126.99548998)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("이태원2동 공영주차장(구)","노외 주차장",String.valueOf(37.54036639),String.valueOf(126.99265839)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("이화여고 앞 노상 공영주차장(구)","노상 주차장",String.valueOf(37.56503654),String.valueOf(126.96860731)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("새문안로2길(노상주차장)(시)","노상 주차장",String.valueOf(37.56886668),String.valueOf(126.97134592)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("중림종합복지센터 부설주차장(구_중림(제5))(구)","노외 주차장",String.valueOf(37.55947076),String.valueOf(126.96616091)));
        publicParkingLotVOArrayList.add(new PublicParkingLotVO("중앙우체국옆 이륜자동차주차장(구)","노상 주차장",String.valueOf(37.56198572),String.valueOf(126.98160593)));
    }

    public void addParkVO() {
        publicParkVOArrayList.add(new PublicParkVO("경희궁",String.valueOf(126.968158),String.valueOf(37.571459)));
        publicParkVOArrayList.add(new PublicParkVO("손기정체육공원",String.valueOf(126.964857126815),String.valueOf(37.5555834468086)));
        publicParkVOArrayList.add(new PublicParkVO("효창근린공원",String.valueOf(126.960238497744),String.valueOf(37.5451839308893)));
        publicParkVOArrayList.add(new PublicParkVO("훈련원근린공원",String.valueOf(126.960238497744),String.valueOf(37.5673056939049)));
        publicParkVOArrayList.add(new PublicParkVO("남산도시자연공원",String.valueOf(126.989931277455),String.valueOf(37.553950352464)));
        publicParkVOArrayList.add(new PublicParkVO("허준공원",String.valueOf(126.964857126815),String.valueOf(37.5555834468086)));
        publicParkVOArrayList.add(new PublicParkVO("탑골근린공원",String.valueOf(126.988364540465),String.valueOf(37.5712043194231)));
        publicParkVOArrayList.add(new PublicParkVO("봉은공원",String.valueOf(126.97772),String.valueOf(37.5464794355433)));
        publicParkVOArrayList.add(new PublicParkVO("덕수궁",String.valueOf(126.97772),String.valueOf(37.5658049)));
    }

    public void addTraditionalMarketVO() {
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("아현골목시장", String.valueOf(37.5567398),String.valueOf(126.9554835)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("공덕시장", String.valueOf(37.5447975),String.valueOf(126.9533474)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("마포시장", String.valueOf(37.5448107),String.valueOf(126.9538425)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("영천시장", String.valueOf(37.5708828),String.valueOf(126.9610464)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("만리시장", String.valueOf(37.5512442),String.valueOf(126.9635953)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("후암시장", String.valueOf(37.550008),String.valueOf(126.9765534)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("용문시장", String.valueOf(37.536037),String.valueOf(126.9601262)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("신흥시장", String.valueOf(37.5452591),String.valueOf(126.9850373)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("세운상가가동", String.valueOf(37.5692642),String.valueOf(126.9952101)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("종각지하쇼핑센터", String.valueOf(37.5705876),String.valueOf(126.9852752)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("남대문시장", String.valueOf(37.5593733),String.valueOf(126.9779334)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("숭례문상가", String.valueOf(37.559404),String.valueOf(126.976203)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("명동 지하도상가", String.valueOf(37.5634922),String.valueOf(126.9816771)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("남대문로 지하상가", String.valueOf(37.5603813),String.valueOf(126.9771774)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("을지입구지하상가", String.valueOf(37.565893),String.valueOf(126.9838088)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("명동역지하도쇼핑센터", String.valueOf(37.5610701),String.valueOf(126.985591)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("시청광장 지하쇼핑센터", String.valueOf(37.5662148),String.valueOf(126.9796594)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("을지지하보도상가", String.valueOf(37.5657876),String.valueOf(126.9872071)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("소공지하도상가", String.valueOf(37.564392),String.valueOf(126.9799971)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("회현지하도상가", String.valueOf(37.5572864),String.valueOf(126.9793216)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("삼익패션타운", String.valueOf(37.5601407),String.valueOf(126.9789572)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("자유상가", String.valueOf(37.5611858),String.valueOf(126.9800889)));
        traditionalMarketVOArrayList.add(new TraditionalMarketVO("인현시장", String.valueOf(37.5639577),String.valueOf(126.9952871)));
    }
}
