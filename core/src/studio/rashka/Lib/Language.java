package studio.rashka.Lib;

import java.util.HashMap;
import java.util.Map;

import studio.rashka.RuDragonGame;

public class Language {

    public Map<String, StringBuilder> textMenu, text;

    public Language() {
        textMenu = new HashMap<String, StringBuilder>();
        text = new HashMap<String, StringBuilder>();
        textMenu.put("version", new StringBuilder("v. 1.0.9 (22)"));
        if (RuDragonGame.getPreference().loadLanguage().equals("ru")) {
            RuDragonGame.getTextures().onLanRus();
            textMenu.put("developerTitle", new StringBuilder("Разработчик"));
            textMenu.put("developerName", new StringBuilder("Табаков Юрий"));
            textMenu.put("flyResult", new StringBuilder("Полёт: "));
            textMenu.put("bonus", new StringBuilder("Бонусы"));
            textMenu.put("storeBonus", new StringBuilder("* 20% даётся единоразово для одного полёта"));
            textMenu.put("storeAngel", new StringBuilder("* даёт возможность воскресить дракона за одну игру"));
            textMenu.put("storeAds", new StringBuilder("** за просмотр видео рекламы даётся вознаграждение или бонус"));
            textMenu.put("actively", new StringBuilder("активно"));
            textMenu.put("upgrade", new StringBuilder("Апгрейд"));
            textMenu.put("achievements", new StringBuilder("Награды"));
            textMenu.put("store", new StringBuilder("Бонусы"));
            textMenu.put("encyclopedia", new StringBuilder("Библиотека"));
            textMenu.put("settings", new StringBuilder("Настройки"));
            textMenu.put("record", new StringBuilder("Рекорд "));
        }
        else if (RuDragonGame.getPreference().loadLanguage().equals("uk")) {
            RuDragonGame.getTextures().onLanUk();
            textMenu.put("developerTitle", new StringBuilder("Розробник"));
            textMenu.put("developerName", new StringBuilder("Табаков Юрій"));
            textMenu.put("flyResult", new StringBuilder("Політ: "));
            textMenu.put("bonus", new StringBuilder("Бонуси"));
            textMenu.put("storeBonus", new StringBuilder("* 20% одноразово для одного польоту"));
            textMenu.put("storeAngel", new StringBuilder("* дозволяє воскресити дракона за одну гру"));
            textMenu.put("storeAds", new StringBuilder("** за перегляд відео реклами дається винагороду або бонус"));
            textMenu.put("actively", new StringBuilder("активно"));
            textMenu.put("upgrade", new StringBuilder("Апгрейд"));
            textMenu.put("achievements", new StringBuilder("Нагороди"));
            textMenu.put("store", new StringBuilder("Бонуси"));
            textMenu.put("encyclopedia", new StringBuilder("Бібліотека"));
            textMenu.put("settings", new StringBuilder("Налаштування"));
            textMenu.put("record", new StringBuilder("Рекорд "));
        }
        else if (RuDragonGame.getPreference().loadLanguage().equals("be")) {
            RuDragonGame.getTextures().onLanBe();
            textMenu.put("developerTitle", new StringBuilder("Распрацоўшчык"));
            textMenu.put("developerName", new StringBuilder("Табаков Юрый"));
            textMenu.put("flyResult", new StringBuilder("Палёт: "));
            textMenu.put("bonus", new StringBuilder("Бонусы"));
            textMenu.put("storeBonus", new StringBuilder("* 20% даецца аднаразова для аднаго палёту"));
            textMenu.put("storeAngel", new StringBuilder("* дае магчымасць ўваскрэсіць дракона за адну гульню"));
            textMenu.put("storeAds", new StringBuilder("** за прагляд відэа рэкламы даецца ўзнагароджанне або бонус"));
            textMenu.put("actively", new StringBuilder("актыўна"));
            textMenu.put("upgrade", new StringBuilder("Апгрэйд"));
            textMenu.put("achievements", new StringBuilder("Ўзнагароды"));
            textMenu.put("store", new StringBuilder("Бонусы"));
            textMenu.put("encyclopedia", new StringBuilder("Бібліятэка"));
            textMenu.put("settings", new StringBuilder("Налады"));
            textMenu.put("record", new StringBuilder("Рэкорд "));
        }
        else {
            RuDragonGame.getTextures().onLanEng();
            textMenu.put("developerTitle", new StringBuilder("Developer"));
            textMenu.put("developerName", new StringBuilder("Tabakov Yuri"));
            textMenu.put("flyResult", new StringBuilder("Fly is "));
            textMenu.put("bonus", new StringBuilder("Bonus"));
            textMenu.put("storeBonus", new StringBuilder("* 20% is given once for one flight"));
            textMenu.put("storeAngel", new StringBuilder("* gives the opportunity to resurrect the dragon for one game"));
            textMenu.put("storeAds", new StringBuilder("** a reward or bonus is given for watching video ads"));
            textMenu.put("actively", new StringBuilder("actively"));
            textMenu.put("upgrade", new StringBuilder("Upgrade"));
            textMenu.put("achievements", new StringBuilder("Rewards"));
            textMenu.put("store", new StringBuilder("Bonus"));
            textMenu.put("encyclopedia", new StringBuilder("Library"));
            textMenu.put("settings", new StringBuilder("Settings"));
            textMenu.put("record", new StringBuilder("Record "));
        }
    }

    public void textEncyclopedia() {
        if (RuDragonGame.getPreference().loadLanguage().equals("ru")) {
            text.put("nullText", new StringBuilder("Нажмите на изображение"));
            text.put("birds", new StringBuilder("Хищные птицы"));
            text.put("trees", new StringBuilder("Деревья"));
            text.put("character", new StringBuilder("Персонажи"));
            text.put("foods", new StringBuilder("Любимая еда"));
            text.put("buzzard", new StringBuilder("Канюк"));
            text.put("buzzardFull", new StringBuilder("Хищная птица из семейства ястребиных, крик напоминает плач, мяуканье."));
            text.put("eagle", new StringBuilder("Орёл"));
            text.put("eagleFull", new StringBuilder("Хищная, сильная птица из семейства соколиных, с изогнутым клювом, живущая в гористых или степных местностях."));
            text.put("falcon", new StringBuilder("Сокол"));
            text.put("falconFull", new StringBuilder("Хищная птица, летающая быстрым парящим полётом. В старину использовавшаяся при охоте на мелких животных и птиц."));
            text.put("hawk", new StringBuilder("Ястреб"));
            text.put("hawkFull", new StringBuilder("Удивительная хищная птица с пёстрым окрасом, благородной осанкой и мощным строением туловища."));
            text.put("korshun", new StringBuilder("Коршун"));
            text.put("korshunFull", new StringBuilder("Хищная птица большого размера, высотой больше полуметра и массой около килограмма. Крылья достаточно узкие, размахом около полутора метров."));
            text.put("orlan", new StringBuilder("Орлан"));
            text.put("orlanFull", new StringBuilder("Хищная птица из подсемейства канюков семейства ястребиных. В отличие от орлов, обладают голой цевкой."));
            text.put("osprey", new StringBuilder("Cкопа"));
            text.put("ospreyFull", new StringBuilder("Хищная птица из семейства скопиных, живущей как в Северном, так и Южном полушарии."));
            text.put("ash", new StringBuilder("Ясень"));
            text.put("ashFull", new StringBuilder("Высокое листопадное дерево, достигающее 40 м. Крона широкая, округлая, светлая, высоко поднята, ветви направлены вверх."));
            text.put("aspen", new StringBuilder("Осина"));
            text.put("aspenFull", new StringBuilder("Лиственное дерево из рода Тополь семейства Ивовые. Осина широко распространена в районах с умеренным и холодным климатом Европы и Азии."));
            text.put("birch", new StringBuilder("Берёза"));
            text.put("birchFull", new StringBuilder("Лиственное дерево с белой корой и с сердцевидными листьями. Относится к листопадным растениям класса двудольные, семейства березовые, отряда букоцветные."));
            text.put("fir", new StringBuilder("Пихта"));
            text.put("firFull", new StringBuilder("Хвойное дерево с мягкой плоской хвоей и с прямо стоящими шишками."));
            text.put("linden", new StringBuilder("Липа"));
            text.put("lindenFull", new StringBuilder("Лиственное дерево с сердцевидными зубчатыми листьями и душистыми медоносными цветками."));
            text.put("maple", new StringBuilder("Клён"));
            text.put("mapleFull", new StringBuilder("Дерево с широкими, у большинства видов фигурными листьями."));
            text.put("oak", new StringBuilder("Дуб"));
            text.put("oakFull", new StringBuilder("Крупное лиственное дерево с крепкой древесиной и плодами-желудями."));
            text.put("pine", new StringBuilder("Сосна"));
            text.put("pineFull", new StringBuilder("Хвойное дерево с длинными иглами и округлыми шишками."));
            text.put("poplar", new StringBuilder("Тополь"));
            text.put("poplarFull", new StringBuilder("Дерево относится к роду двудомных листопадных быстрорастущих деревьев семейства Ивовые."));
            text.put("spruce", new StringBuilder("Ель"));
            text.put("spruceFull", new StringBuilder("Хвойное вечнозелёное дерево с кроной конусообразной формы и висячими шишками."));
            text.put("willow", new StringBuilder("Ива"));
            text.put("willowFull", new StringBuilder("Дерево любит влажность и селится в сырых местах, в сухих же местах (на склонах, песках и т.п.) и на болотах растёт сравнительно редко. Встречается ива и в лесах, как подмесь к другим деревьям. Относится к семейству Ивовых."));
            text.put("babaYaga", new StringBuilder("Баба Яга"));
            text.put("babaYagaFull", new StringBuilder("Старуха-колдунья русских сказок. Красивая и вечно молодая."));
            text.put("duck", new StringBuilder("Утка"));
            text.put("duckFull", new StringBuilder("Водоплавающая птица средних и небольших размеров с относительно короткой шеей и цевкой, покрытой спереди поперечными щитками."));
            text.put("kine", new StringBuilder("Корова"));
            text.put("kineFull", new StringBuilder("Парнокопытное жвачное животное семейства полорогих. Разводится для получения мяса, молока и кожи."));
            text.put("pig", new StringBuilder("Свинья"));
            text.put("pigFull", new StringBuilder("Парнокопытное млекопитающее с крупным телом и короткими ногами, домашний вид которого разводят для получения мяса, сала, щетины."));
            text.put("goat", new StringBuilder("Козёл"));
            text.put("goatFull", new StringBuilder("Домашнее жвачное животное, с длинными извилистыми рогами и бородой у самца. Разводят для получения молока и мяса."));
            text.put("CasketXP", new StringBuilder("Ларец"));
            text.put("CasketXPFull", new StringBuilder("Ларец Кощея хранит в себе большие богатства, накопленные за долгие годы его бессмертия."));
            text.put("CasketXam", new StringBuilder("Ларец"));
            text.put("CasketXamFull", new StringBuilder("В ларце Кощея хранятся его очень старые кости, а Горыныч любит вкусные и хрустящие косточки."));
        }
        else if (RuDragonGame.getPreference().loadLanguage().equals("uk")) {
            text.put("nullText", new StringBuilder("Натисніть на зображення"));
            text.put("birds", new StringBuilder("Хижі птахи"));
            text.put("trees", new StringBuilder("Дерева"));
            text.put("character", new StringBuilder("Персонажі"));
            text.put("foods", new StringBuilder("Улюблена їжа"));
            text.put("buzzard", new StringBuilder("Канюк"));
            text.put("buzzardFull", new StringBuilder("Хижий птах із сімейства яструбиних, крик нагадує плач, нявкання."));
            text.put("eagle", new StringBuilder("Орел"));
            text.put("eagleFull", new StringBuilder("Хижа, сильна птах із сімейства соколиних, із зігнутим дзьобом, живе в гористих або степових місцевостях."));
            text.put("falcon", new StringBuilder("Сокіл"));
            text.put("falconFull", new StringBuilder("Хижий птах, що літає швидким ширяє польотом. За старих часів використовувалася при полюванні на дрібних тварин і птахів."));
            text.put("hawk", new StringBuilder("Яструб"));
            text.put("hawkFull", new StringBuilder("Дивовижна хижий птах з строкатим забарвленням, благородної поставою і потужним будовою тулуба."));
            text.put("korshun", new StringBuilder("Коршун"));
            text.put("korshunFull", new StringBuilder("Хижий птах великого розміру, висотою більше півметра і масою близько кілограма. Крила досить вузькі, розмахом близько півтора метрів."));
            text.put("orlan", new StringBuilder("Орлан"));
            text.put("orlanFull", new StringBuilder("Хижий птах з підродини канюков сімейства яструбиних. На відміну від орлів, мають голою цівкою."));
            text.put("osprey", new StringBuilder("Скопа"));
            text.put("ospreyFull", new StringBuilder("Хижий птах із сімейства Скопин, що живе як у Північному, так і Південній півкулі."));
            text.put("ash", new StringBuilder("Ясень"));
            text.put("ashFull", new StringBuilder("Висока листопадне дерево, що досягає 40 м. Крона широка, округла, світла, високо піднята, гілки спрямовані вгору."));
            text.put("aspen", new StringBuilder("Осика"));
            text.put("aspenFull", new StringBuilder("Листяних дерев з роду Тополь сімейства Вербові. Осика широко поширена в районах з помірним і холодним кліматом Європи і Азії."));
            text.put("birch", new StringBuilder("Береза"));
            text.put("birchFull", new StringBuilder("Листяних дерев з білою корою і з серцеподібним листям. Відноситься до листопадним рослинам класу дводольні, сімейства березові, загону букоцвіті."));
            text.put("fir", new StringBuilder("Ялиця"));
            text.put("firFull", new StringBuilder("Хвойне дерево з м'якою плоскою хвоєю і з прямо стоять шишками."));
            text.put("linden", new StringBuilder("Липа"));
            text.put("lindenFull", new StringBuilder("Листяних дерев з серцеподібними зубчастими листками і запашними медоносними квітками."));
            text.put("maple", new StringBuilder("Клен"));
            text.put("mapleFull", new StringBuilder("Дерево з широкими, у більшості видів фігурними листям."));
            text.put("oak", new StringBuilder("Дуб"));
            text.put("oakFull", new StringBuilder("Велике листяних дерев з міцною деревиною та плодами-жолудями."));
            text.put("pine", new StringBuilder("Сосна"));
            text.put("pineFull", new StringBuilder("Хвойне дерево з довгими голками і округлими шишками."));
            text.put("poplar", new StringBuilder("Тополя"));
            text.put("poplarFull", new StringBuilder("Дерево відноситься до роду дводомних листопадних швидкорослих дерев сімейства Вербові."));
            text.put("spruce", new StringBuilder("Ялина"));
            text.put("spruceFull", new StringBuilder("Хвойне вічнозелене дерево з кроною конусоподібної форми і висячими шишками."));
            text.put("willow", new StringBuilder("Іва"));
            text.put("willowFull", new StringBuilder("Дерево любить вологість і селиться в сирих місцях, в сухих же місцях (на схилах, пісках і т.п.) і на болотах росте порівняно рідко. Зустрічається верба і в лісах, як підміна до інших дерев. Відноситься до сімейства вербових."));
            text.put("babaYaga", new StringBuilder("Баба Яга"));
            text.put("babaYagaFull", new StringBuilder("Стара-чаклунка російських казок. Красива і вічно молода."));
            text.put("duck", new StringBuilder("Качка"));
            text.put("duckFull", new StringBuilder("Водоплавна птиця середніх і невеликих розмірів з відносно короткою шиєю і цівкою, покритою спереду поперечними щитками."));
            text.put("kine", new StringBuilder("Корова"));
            text.put("kineFull", new StringBuilder("Парнокопитна жуйних тварин сімейства полорогих. Розлучається для отримання м'яса, молока і шкіри."));
            text.put("pig", new StringBuilder("Свиня"));
            text.put("pigFull", new StringBuilder("Парнокопитна ссавець з великим тілом і короткими ногами, домашній вид якого розводять для отримання м'яса, сала, щетини."));
            text.put("goat", new StringBuilder("Козел"));
            text.put("goatFull", new StringBuilder("Домашнє жуйних тварин, з довгими звивистими рогами і бородою у самця. Розводять для отримання молока і м'яса."));
            text.put("CasketXP", new StringBuilder("Скринька"));
            text.put("CasketXPFull", new StringBuilder("Скринька Кощія зберігає в собі великі багатства, накопичені за довгі роки його безсмертя."));
            text.put("CasketXam", new StringBuilder("Скринька"));
            text.put("CasketXamFull", new StringBuilder("У скриньці Кощія зберігаються його дуже старі кістки, а Горинич любить смачні та хрусткі кісточки."));
        }
        else if (RuDragonGame.getPreference().loadLanguage().equals("be")) {
            text.put("nullText", new StringBuilder("Націсніце на малюнак"));
            text.put("birds", new StringBuilder("Драпежныя птушкі"));
            text.put("trees", new StringBuilder("Дрэвы"));
            text.put("character", new StringBuilder("Персанажы"));
            text.put("foods", new StringBuilder("Любімая ежа"));
            text.put("buzzard", new StringBuilder("Канюк"));
            text.put("buzzardFull", new StringBuilder("Драпежная птушка з сямейства ястрабіных, крык нагадвае плач, мяўканне."));
            text.put("eagle", new StringBuilder("Арол"));
            text.put("eagleFull", new StringBuilder("Драпежная, моцная птушка з сямейства сакаліных, з выгнутым дзюбай, якая жыве ў гарыстых або стэпавых мясцовасцях."));
            text.put("falcon", new StringBuilder("Сокал"));
            text.put("falconFull", new StringBuilder("Драпежная птушка, лятаючая хуткім парылы палётам. Даўней якая выкарыстоўвалася пры паляванні на дробных жывёл і птушак."));
            text.put("hawk", new StringBuilder("Ястраб"));
            text.put("hawkFull", new StringBuilder("Дзіўная драпежная птушка з пярэстым афарбоўкай, высакароднай выправай і магутным будынкам тулава."));
            text.put("korshun", new StringBuilder("Каршун"));
            text.put("korshunFull", new StringBuilder("Драпежная птушка вялікага памеру, вышынёй больш за паўметра і масай каля кілаграма. Крылы дастаткова вузкія, размахам каля паўтара метра."));
            text.put("orlan", new StringBuilder("Арлан"));
            text.put("orlanFull", new StringBuilder("Драпежная птушка з подсемейства канюкоў сямейства ястрабіных. У адрозненне ад арлоў, валодаюць голай цэўку."));
            text.put("osprey", new StringBuilder("Скопа"));
            text.put("ospreyFull", new StringBuilder("Драпежная птушка з сямейства скопиных, якая жыве як у Паўночным, так і Паўднёвым паўшар'і."));
            text.put("ash", new StringBuilder("Ясень"));
            text.put("ashFull", new StringBuilder("Высокае лістападныя дрэва, якое дасягае 40 м. Крона шырокая, круглявая, светлая, высока паднятая, галіны накіраваныя ўверх."));
            text.put("aspen", new StringBuilder("Асіна"));
            text.put("aspenFull", new StringBuilder("Ліставое дрэва з роду Таполя сямейства вярбовых. Асіна шырока распаўсюджаная ў раёнах з умераным і халодным кліматам Еўропы і Азіі."));
            text.put("birch", new StringBuilder("Бяроза"));
            text.put("birchFull", new StringBuilder("Ліставое дрэва з белай карой і з сэрцападобныя лісцем. Ставіцца да лістападныя раслінам класа Двухдольныя, сямейства бярозавыя, атрада букоцветные."));
            text.put("fir", new StringBuilder("Піхта"));
            text.put("firFull", new StringBuilder("Хваёвае дрэва з мяккай плоскай ігліцай і з прама стаяць шышкамі."));
            text.put("linden", new StringBuilder("Ліпа"));
            text.put("lindenFull", new StringBuilder("Ліставое дрэва з сэрцападобныя зубчастымі лісцем і духмянымі меданоснымі кветкамі."));
            text.put("maple", new StringBuilder("Клён"));
            text.put("mapleFull", new StringBuilder("Дрэва з шырокімі, у большасці відаў фігурнымі лісцем."));
            text.put("oak", new StringBuilder("Дуб"));
            text.put("oakFull", new StringBuilder("Буйное ліставое дрэва з моцнай драўнінай і пладамі-жалудамі."));
            text.put("pine", new StringBuilder("Хвоя"));
            text.put("pineFull", new StringBuilder("Хваёвае дрэва з доўгімі іголкамі і круглявымі шышкамі."));
            text.put("poplar", new StringBuilder("Таполя"));
            text.put("poplarFull", new StringBuilder("Дрэва адносіцца да роду двудомная лістападных хуткарослых дрэў сямейства вярбовых."));
            text.put("spruce", new StringBuilder("Елка"));
            text.put("spruceFull", new StringBuilder("Іглічнае вечназялёнае дрэва з кронай конусападобнай формы і вісячымі шышкамі."));
            text.put("willow", new StringBuilder("Вярба"));
            text.put("willowFull", new StringBuilder("Дрэва любіць вільготнасць і селіцца ў сырых месцах, у сухіх жа месцах (на схілах, пясках і да т.п.) і на балотах расце параўнальна рэдка. Сустракаецца вярба і ў лясах, як падмена да іншых дрэвах. Ставіцца да сямейства вярбовых."));
            text.put("babaYaga", new StringBuilder("Баба Яга"));
            text.put("babaYagaFull", new StringBuilder("Старая-вядзьмарка рускіх казак. Прыгожая і вечна маладая."));
            text.put("duck", new StringBuilder("Качка"));
            text.put("duckFull", new StringBuilder("Вадаплаўная птушка сярэдніх і невялікіх памераў з адносна кароткай шыяй і цэўку, пакрытай спераду папярочнымі шчыткамі."));
            text.put("kine", new StringBuilder("Карова"));
            text.put("kineFull", new StringBuilder("Парнакапытных жуйных жывёл сямейства полорогих. Разводзіцца для атрымання мяса, малака і скуры."));
            text.put("pig", new StringBuilder("Свіння"));
            text.put("pigFull", new StringBuilder("Парнакапытных млекакормячых з буйным целам і кароткімі нагамі, хатні выгляд якога разводзяць для атрымання мяса, сала, шчаціння."));
            text.put("goat", new StringBuilder("Казёл"));
            text.put("goatFull", new StringBuilder("Хатняе жуйных жывёл, з доўгімі звілістымі рагамі і барадой у самца. Разводзяць для атрымання малака і мяса."));
            text.put("CasketXP", new StringBuilder("Куфэрак"));
            text.put("CasketXPFull", new StringBuilder("Куфэрак Кашчэя захоўвае ў сабе вялікія багацці, назапашаныя за доўгія гады яго неўміручасці."));
            text.put("CasketXam", new StringBuilder("Куфэрак"));
            text.put("CasketXamFull", new StringBuilder("У скрыні Кашчэя захоўваюцца яго вельмі старыя косці, а Гарыныч любіць смачныя і храбусткія костачкі."));
        }
        else {
            text.put("nullText", new StringBuilder("Click on image"));
            text.put("birds", new StringBuilder("Predator birds"));
            text.put("trees", new StringBuilder("Trees"));
            text.put("character", new StringBuilder("Character"));
            text.put("foods", new StringBuilder("Favorite food"));
            text.put("buzzard", new StringBuilder("Buzzard"));
            text.put("buzzardFull", new StringBuilder("A large hawklike bird of prey with broad wings and a rounded tail, typically seen soaring in wide circles."));
            text.put("eagle", new StringBuilder("Eagle"));
            text.put("eagleFull", new StringBuilder("A large bird of prey with a massive hooked bill and long broad wings, renowned for its keen sight and powerful soaring flight."));
            text.put("falcon", new StringBuilder("Falcon"));
            text.put("falconFull", new StringBuilder("A diurnal bird of prey with long pointed wings and a notched beak, typically catching prey by diving on it from above."));
            text.put("hawk", new StringBuilder("Hawk"));
            text.put("hawkFull", new StringBuilder("A diurnal bird of prey with broad rounded wings and a long tail, typically taking prey by surprise with a short chase."));
            text.put("korshun", new StringBuilder("Korshun"));
            text.put("korshunFull", new StringBuilder("The bird of prey is large, about half a meter high and about a kilogram in weight. The wings are rather narrow, with a span of about one and a half meters."));
            text.put("orlan", new StringBuilder("Orlan"));
            text.put("orlanFull", new StringBuilder("A bird of prey from the subfamily of the buzzard family of hawks. In contrast to the eagles, they have a bare forge."));
            text.put("osprey", new StringBuilder("Osprey"));
            text.put("ospreyFull", new StringBuilder("A large fish-eating bird of prey with long narrow wings and a white underside and crown, found throughout the world."));
            text.put("ash", new StringBuilder("Ash"));
            text.put("ashFull", new StringBuilder("A tree with silver-gray bark and compound leaves. The ash is widely distributed throughout north temperate regions where it can form forests."));
            text.put("aspen", new StringBuilder("Aspen"));
            text.put("aspenFull", new StringBuilder("A poplar tree with rounded, long-stalked, and typically coarsely toothed leaves that tremble in even a slight breeze."));
            text.put("birch", new StringBuilder("Birch"));
            text.put("birchFull", new StringBuilder("A slender, fast-growing tree that has thin bark (often peeling) and bears catkins. Birch trees grow chiefly in north temperate regions, some reaching the northern limit of tree growth."));
            text.put("fir", new StringBuilder("Fir"));
            text.put("firFull", new StringBuilder("An evergreen coniferous tree with upright cones and flat needle-shaped leaves, typically arranged in two rows. Firs are an important source of timber and resins."));
            text.put("linden", new StringBuilder("Linden"));
            text.put("lindenFull", new StringBuilder("A deciduous tree with heart-shaped leaves and fragrant yellowish blossoms, native to north temperate regions. The pale soft timber is used for carving and furniture."));
            text.put("maple", new StringBuilder("Maple"));
            text.put("mapleFull", new StringBuilder("A tree or shrub with lobed leaves, winged fruits, and colorful autumn foliage, grown as an ornamental or for its timber or syrupy sap."));
            text.put("oak", new StringBuilder("Oak"));
            text.put("oakFull", new StringBuilder("A tree that bears acorns as fruit, and typically has lobed deciduous leaves. Oaks are common in many north temperate forests and are an important source of hard and durable wood used chiefly in construction, furniture, and (formerly) shipbuilding."));
            text.put("pine", new StringBuilder("Pine"));
            text.put("pineFull", new StringBuilder("An evergreen coniferous tree that has clusters of long needle-shaped leaves. Many kinds are grown for their soft timber, which is widely used for furniture and pulp, or for tar and turpentine."));
            text.put("poplar", new StringBuilder("Poplar"));
            text.put("poplarFull", new StringBuilder("A tall, fast-growing tree of north temperate regions, widely grown in shelter belts and for timber and pulp."));
            text.put("spruce", new StringBuilder("Spruce"));
            text.put("spruceFull", new StringBuilder("A widespread coniferous tree that has a distinctive conical shape and hanging cones, widely grown for timber, pulp, and Christmas trees."));
            text.put("willow", new StringBuilder("Willow"));
            text.put("willowFull", new StringBuilder("A tree or shrub of temperate climates that typically has narrow leaves, bears catkins, and grows near water. Its pliant branches yield osiers for basketry, and its wood has various uses."));
            text.put("babaYaga", new StringBuilder("BabaYaga"));
            text.put("babaYagaFull", new StringBuilder("The old woman is a sorceress of Russian fairy tales. Beautiful and always young."));
            text.put("duck", new StringBuilder("Duck"));
            text.put("duckFull", new StringBuilder("A waterbird with a broad blunt bill, short legs, webbed feet, and a waddling gait."));
            text.put("kine", new StringBuilder("Kine"));
            text.put("kineFull", new StringBuilder("A cloven-hoofed ruminant animal of the Polaroid family. It is bred for obtaining meat, milk and skin."));
            text.put("pig", new StringBuilder("Pig"));
            text.put("pigFull", new StringBuilder("An omnivorous domesticated hoofed mammal with sparse bristly hair and a flat snout for rooting in the soil, kept for its meat."));
            text.put("goat", new StringBuilder("Goat"));
            text.put("goatFull", new StringBuilder("A hardy domesticated ruminant animal that has backward curving horns and (in the male) a beard. It is kept for its milk and meat and is noted for its lively and frisky behavior."));
            text.put("CasketXP", new StringBuilder("Casket"));
            text.put("CasketXPFull", new StringBuilder("The casket of Koshchei keeps in itself the great riches accumulated over the long years of his immortality."));
            text.put("CasketXam", new StringBuilder("Casket"));
            text.put("CasketXamFull", new StringBuilder("In the casket of Koshchei his very old bones are kept and Gorynych likes tasty and crispy bones."));
        }
    }

    public void textAchievements() {
        if (RuDragonGame.getPreference().loadLanguage().equals("ru")) {
            text.put("nullText", new StringBuilder("Нажмите на изображение"));
            text.put("textInfo", new StringBuilder("* каждое число показывает сколько осталось до получения вознаграждения."));
            text.put("lvlUp", new StringBuilder("Новый уровень"));
            text.put("lvlUpFull", new StringBuilder("Пролетайте большие расстояния и получайте опыт для поднятия уровня.\n(достигните указанного уровня)"));
            text.put("start", new StringBuilder("Старт - Финиш"));
            text.put("startFull", new StringBuilder("Отправляйте своего дракона в полёт, так как ему нужно расправлять свои крылья.\n(начните игру указанное количество раз)"));
            text.put("fly", new StringBuilder("Летун"));
            text.put("flyFull", new StringBuilder("Драконы любят летать и ваш не исключение. Выпускайте почаще своего дракона на лётные прогулки.\n(пролетите в воздухе указанное количество метров)"));
            text.put("run", new StringBuilder("Бегун"));
            text.put("runFull", new StringBuilder("Иногда драконам полезно размять свои могучие лапы, поэтому пусть немного побегают.\n(пролетите над землёй указанное количество метров)"));
            text.put("dragonUp", new StringBuilder("Могучий дракон"));
            text.put("dragonUpFull", new StringBuilder("Дракон с возрастом становится могущественнее, но без особого внимания он этого не достигнет. Улучшайте его боевые характеристики.\n(улучшайте характеристики дракона)"));
            text.put("homeLove", new StringBuilder("Домашний уют"));
            text.put("homeLoveFull", new StringBuilder("Чтобы дракон был полон сил и счастлив, ему нужен комфорт и уют. Обустраивайте пещеру и радуйте своего дракона.\n(покупайте вещи для пещеры)"));
            text.put("iBest", new StringBuilder("Ты лучший"));
            text.put("iBestFull", new StringBuilder("Побей свой лучший полёт и стань гораздо сильнее, чем был. С каждым побитым рекордом ты становишься лучше.\n(побейте свой рекорд указанное количество раз)"));
            text.put("banker", new StringBuilder("Припасливый"));
            text.put("bankerFull", new StringBuilder("Любой уважаемый себя дракон будет запасаться провизией, чтобы не голодать морозной зимой.\n(накопите указанное количество мяса на складе)"));
            text.put("eatLove", new StringBuilder("Обжорка"));
            text.put("eatLoveFull", new StringBuilder("Любой подрастающий дракон любит сытно покушать вкусного свежего мяса.\n(съешьте указанное количество мяса за всю игру)"));
            text.put("duckLove", new StringBuilder("Любитель утятины"));
            text.put("duckLoveFull", new StringBuilder("Да ваш дракон просто обожает есть свежих уточек.\n(съешьте указанное количество уток)"));
            text.put("goatLove", new StringBuilder("Любитель козлятины"));
            text.put("goatLoveFull", new StringBuilder("Да ваш дракон просто обожает есть свежих козлят.\n(съешьте указанное количество козлят)"));
            text.put("pigLove", new StringBuilder("Любитель свининки"));
            text.put("pigLoveFull", new StringBuilder("Да ваш дракон просто обожает есть вкусных и ароматных свинок.\n(съешьте указанное количество свиней)"));
            text.put("kineLove", new StringBuilder("Любитель телятины"));
            text.put("kineLoveFull", new StringBuilder("Да ваш дракон настоящий гурман, раз ест только отборную и мясистую телятину.\n(съешьте указанное количество коров)"));
        }
        else if (RuDragonGame.getPreference().loadLanguage().equals("uk")) {
            text.put("nullText", new StringBuilder("Натисніть на зображення"));
            text.put("textInfo", new StringBuilder("* кожне число показує скільки залишилося до отримання винагороди."));
            text.put("lvlUp", new StringBuilder("Новий рівень"));
            text.put("lvlUpFull", new StringBuilder("Пролітайте великі відстані і отримуйте досвід для підняття рівня.\n(досягнете зазначеного рівня)"));
            text.put("start", new StringBuilder("Старт - Фініш"));
            text.put("startFull", new StringBuilder("Надсилайте свого дракона в політ, так як йому потрібно розправляти свої крила.\n(почніть гру вказану кількість разів)"));
            text.put("fly", new StringBuilder("Літун"));
            text.put("flyFull", new StringBuilder("Дракони люблять літати і ваш не виняток. Випускайте частіше свого дракона на льотні прогулянки.\n(пролетите в повітрі вказану кількість метрів)"));
            text.put("run", new StringBuilder("Бігун"));
            text.put("runFull", new StringBuilder("Іноді драконам корисно розім'яти свої могутні лапи, тому нехай трохи побігають.\n(пролетите над землею вказану кількість метрів)"));
            text.put("dragonUp", new StringBuilder("Могутній дракон"));
            text.put("dragonUpFull", new StringBuilder("Дракон з віком стає більш могутнім, але без особливої уваги він цього не досягне. Покращуйте його бойові характеристики.\n(покращуйте характеристики дракона)"));
            text.put("homeLove", new StringBuilder("Домашній затишок"));
            text.put("homeLoveFull", new StringBuilder("Щоб дракон був сповнений сил і щасливий, йому потрібен комфорт і затишок. Облаштовувати печеру і радуйте свого дракона.\n(купуйте речі для печери)"));
            text.put("iBest", new StringBuilder("Ти кращий"));
            text.put("iBestFull", new StringBuilder("Побий свій кращий політ і стань набагато сильніше, ніж був. З кожним побитим рекордом ти стаєш краще.\n(побийте свій рекорд вказану кількість разів)"));
            text.put("banker", new StringBuilder("Пріпаслівий"));
            text.put("bankerFull", new StringBuilder("Будь шановний себе дракон буде запасатися харчами, щоб не голодувати морозною зимою.\n(заробите вказану кількість м'яса на складі)"));
            text.put("eatLove", new StringBuilder("Обжорка"));
            text.put("eatLoveFull", new StringBuilder("Будь підростаючий дракон любить ситно поїсти смачного свіжого м'яса.\n(з'їжте вказану кількість м'яса за всю гру)"));
            text.put("duckLove", new StringBuilder("Любитель качатини"));
            text.put("duckLoveFull", new StringBuilder("Так ваш дракон просто обожнює є свіжих качечок.\n(з'їжте вказану кількість качок)"));
            text.put("goatLove", new StringBuilder("Любитель козлятини"));
            text.put("goatLoveFull", new StringBuilder("Так ваш дракон просто обожнює є свіжих козлів.\n(з'їжте вказану кількість козлів)"));
            text.put("pigLove", new StringBuilder("Любитель свининки"));
            text.put("pigLoveFull", new StringBuilder("Так ваш дракон просто обожнює є смачних і ароматних свинок.\n(з'їжте вказану кількість свиней)"));
            text.put("kineLove", new StringBuilder("Любитель телятини"));
            text.put("kineLoveFull", new StringBuilder("Так ваш дракон справжній гурман, раз їсть тільки добірну і м'ясисту телятину.\n(з'їжте вказану кількість корів)"));
        }
        else if (RuDragonGame.getPreference().loadLanguage().equals("be")) {
            text.put("nullText", new StringBuilder("Націсніце на малюнак"));
            text.put("textInfo", new StringBuilder("* кожны лік паказвае колькі засталося да атрымання ўзнагароды."));
            text.put("lvlUp", new StringBuilder("Новы ўзровень"));
            text.put("lvlUpFull", new StringBuilder("Пралятаў вялікія адлегласці і атрымлівайце вопыт для ўзняцця ўзроўню.\n(дасягніце названага ўзроўню)"));
            text.put("start", new StringBuilder("Старт - Фініш"));
            text.put("startFull", new StringBuilder("Адпраўляйце свайго дракона ў палёт, бо яму трэба распрамляць свае крылы.\n(пачніце гульню паказанае колькасць разоў)"));
            text.put("fly", new StringBuilder("Лятун"));
            text.put("flyFull", new StringBuilder("Цмокі любяць лётаць і ваш не выключэнне. Выпускайце часцей свайго дракона на лётныя прагулкі.\n(пралёт у паветры паказанае колькасць метраў)"));
            text.put("run", new StringBuilder("Бягун"));
            text.put("runFull", new StringBuilder("Часам драконам карысна расцерці свае магутныя лапы, таму хай трохі пабегаць.\n(пралятаючы над зямлёй паказанае колькасць метраў)"));
            text.put("dragonUp", new StringBuilder("Магутны цмок"));
            text.put("dragonUpFull", new StringBuilder("Цмок з узростам становіцца магутнейшы, але без асаблівай увагі ён гэтага не дасягне. Паляпшае яго баявыя характарыстыкі.\n(паляпшае характарыстыкі дракона)"));
            text.put("homeLove", new StringBuilder("Хатні ўтульнасць"));
            text.put("homeLoveFull", new StringBuilder("Каб цмок быў поўны сіл і шчаслівы, яму патрэбны камфорт і ўтульнасць. Ўладкоўвацца пячору і цешце свайго дракона.\n(купляйце рэчы для пячоры)"));
            text.put("iBest", new StringBuilder("Ты лепшы"));
            text.put("iBestFull", new StringBuilder("Пабі свой лепшы палёт і стань значна мацней, чым быў. З кожным пабітым рэкордам ты становішся лепш.\n(пабеце свой рэкорд паказанае колькасць разоў)"));
            text.put("banker", new StringBuilder("Припасливый"));
            text.put("bankerFull", new StringBuilder("Любы паважаны сябе цмок будзе запасацца правізіяй, каб не галадаць марознай зімой.\n(назапасіць паказанае колькасць мяса на складзе)"));
            text.put("eatLove", new StringBuilder("Обжорка"));
            text.put("eatLoveFull", new StringBuilder("Любы падрастаюць цмок любіць сытна паесці смачнага свежага мяса.\n(з'ешце паказанае колькасць мяса за ўсю гульню)"));
            text.put("duckLove", new StringBuilder("Аматар качацінай"));
            text.put("duckLoveFull", new StringBuilder("Ды ваш цмок проста любіць ёсць свежых качачак.\n(з'ешце паказанае колькасць качак)"));
            text.put("goatLove", new StringBuilder("Аматар казляціны"));
            text.put("goatLoveFull", new StringBuilder("Ды ваш цмок проста любіць ёсць свежых казлянят.\n(з'ешце паказанае колькасць казлянят)"));
            text.put("pigLove", new StringBuilder("Аматар свінінкай"));
            text.put("pigLoveFull", new StringBuilder("Ды ваш цмок проста любіць ёсць смачных і духмяных свінак.\n(з'ешце паказанае колькасць свіней)"));
            text.put("kineLove", new StringBuilder("Аматар цяляціны"));
            text.put("kineLoveFull", new StringBuilder("Ды ваш цмок сапраўдны гурман, раз есць толькі адборная і мясістую цяляціну.\n(з'ешце паказанае колькасць кароў)"));
        }
        else {
            text.put("nullText", new StringBuilder("Click on image"));
            text.put("textInfo", new StringBuilder("* each number shows how much is left before the reward is received."));
            text.put("lvlUp", new StringBuilder("New level"));
            text.put("lvlUpFull", new StringBuilder("Fly long distances and gain experience to raise the level.\n(reach the specified level)"));
            text.put("start", new StringBuilder("Start - Finish"));
            text.put("startFull", new StringBuilder("Send your dragon to flight, as he needs to spread his wings.\n(start the game a specified number of times)"));
            text.put("fly", new StringBuilder("Lover of flying"));
            text.put("flyFull", new StringBuilder("Dragons love to fly and yours is no exception. Issue your dragon often for flight trips.\n(fly in the air indicated number of meters)"));
            text.put("run", new StringBuilder("Runner"));
            text.put("runFull", new StringBuilder("Sometimes dragons are useful to stretch their mighty paws, so let them run a little.\n(fly over the ground specified number of meters)"));
            text.put("dragonUp", new StringBuilder("Mighty Dragon"));
            text.put("dragonUpFull", new StringBuilder("The dragon becomes more powerful with age, but without special attention he will not achieve it. Improve its combat characteristics.\n(upgrading of dragon)"));
            text.put("homeLove", new StringBuilder("Homeliness"));
            text.put("homeLoveFull", new StringBuilder("The dragon was full of strength and happy, he needed comfort and coziness. Equip a cave and celebrate your dragon.\n(buy things for the cave)"));
            text.put("iBest", new StringBuilder("The best"));
            text.put("iBestFull", new StringBuilder("Beat your best flight and be much stronger than you were. With every beaten record you get better.\n(beat your record specified number of times)"));
            text.put("banker", new StringBuilder("Thrifty"));
            text.put("bankerFull", new StringBuilder("Any reputable dragon will be stocked with provisions to avoid starving in a frosty winter.\n(store the specified amount of meat in the warehouse)"));
            text.put("eatLove", new StringBuilder("Heavy eater"));
            text.put("eatLoveFull", new StringBuilder("Any growing dragon likes to eat heartily tasty and fresh meat.\n(eat the indicated amount of meat for the whole game)"));
            text.put("duckLove", new StringBuilder("Duthouse lover"));
            text.put("duckLoveFull", new StringBuilder("Your dragon simply adores eating fresh ducks.\n(eat the specified number of ducks)"));
            text.put("goatLove", new StringBuilder("The goat is lover"));
            text.put("goatLoveFull", new StringBuilder("Your dragon simply adores eating fresh goats.\n(eat the indicated number of goats)"));
            text.put("pigLove", new StringBuilder("Pork lovers"));
            text.put("pigLoveFull", new StringBuilder("Your dragon simply adores eating delicious and fragrant gilts.\n(eat a specified number of pigs)"));
            text.put("kineLove", new StringBuilder("Lover of veal"));
            text.put("kineLoveFull", new StringBuilder("Your dragon is a real gourmet, once eating only selected and meaty veal.\n(eat the indicated number of cows)"));
        }
    }

    public void textUpgradeMenu() {
        if (RuDragonGame.getPreference().loadLanguage().equals("ru")) {
            text.put("nullText", new StringBuilder("Нажмите на изображение"));
            text.put("flyUp", new StringBuilder("Сильный дракон - опасный дракон"));
            text.put("flyUpFull", new StringBuilder("взлетает быстрее на 1%"));
            text.put("flyDown", new StringBuilder("Сытный дракон - тяжелый дракон"));
            text.put("flyDownFull", new StringBuilder("падает быстрее на 1%"));
            text.put("fendOff", new StringBuilder("Проворный дракон - живучий дракон"));
            text.put("fendOffFull", new StringBuilder("позволяет парировать в воздухе и облетать препятствия"));
            text.put("roar", new StringBuilder("Свирепый дракон - опасный дракон"));
            text.put("roarFull", new StringBuilder("+1 свирепый рёв"));
            text.put("bonusXP", new StringBuilder("Умный дракон - хитрый дракон"));
            text.put("bonusXPFull", new StringBuilder("полученный опыт увеличивается на 1%"));
            text.put("bonusHam", new StringBuilder("Голодный дракон - прожорливый дракон"));
            text.put("bonusHamFull", new StringBuilder("съеденное мясо увеличивается на 1%"));
            text.put("buyTree", new StringBuilder("Озеленение территории"));
            text.put("buyTreeFull", new StringBuilder("можно посадить деревья рядом с домом"));
            text.put("buyBirds", new StringBuilder("Небесная охрана"));
            text.put("buyBirdsFull", new StringBuilder("вырасти и воспитай настоящих воздушных охотников"));
            text.put("buyLiveStock", new StringBuilder("Дракон теперь фермер?"));
            text.put("buyLiveStockFull", new StringBuilder("пора разводить домашний скот"));
            text.put("upLVL", new StringBuilder("Для открытия нужен уровень дракона - "));
        }
        else if (RuDragonGame.getPreference().loadLanguage().equals("uk")) {
            text.put("nullText", new StringBuilder("Натисніть на зображення"));
            text.put("flyUp", new StringBuilder("Сильний дракон - небезпечний дракон"));
            text.put("flyUpFull", new StringBuilder("злітає швидше на 1%"));
            text.put("flyDown", new StringBuilder("Ситний дракон - важкий дракон"));
            text.put("flyDownFull", new StringBuilder("падає швидше на 1%"));
            text.put("fendOff", new StringBuilder("Перевірений дракон - живучий дракон"));
            text.put("fendOffFull", new StringBuilder("дозволяє парирувати в повітрі і облітати перешкоди"));
            text.put("roar", new StringBuilder("Лютий дракон - небезпечний дракон"));
            text.put("roarFull", new StringBuilder("+1 лютий рев"));
            text.put("bonusXP", new StringBuilder("Розумний дракон - хитрий дракон"));
            text.put("bonusXPFull", new StringBuilder("отриманий досвід збільшується на 1%"));
            text.put("bonusHam", new StringBuilder("Голодний дракон - ненажерливий дракон"));
            text.put("bonusHamFull", new StringBuilder("з'їдене м'ясо збільшується на 1%"));
            text.put("buyTree", new StringBuilder("Озеленення території"));
            text.put("buyTreeFull", new StringBuilder("можна посадити дерева поруч з будинком"));
            text.put("buyBirds", new StringBuilder("Небесна охорона"));
            text.put("buyBirdsFull", new StringBuilder("вирости і виховай справжніх повітряних мисливців"));
            text.put("buyLiveStock", new StringBuilder("Дракон тепер фермер?"));
            text.put("buyLiveStockFull", new StringBuilder("пора розводити худобу"));
            text.put("upLVL", new StringBuilder("Для відкриття потрібен рівень дракона - "));
        }
        else if (RuDragonGame.getPreference().loadLanguage().equals("be")) {
            text.put("nullText", new StringBuilder("Націсніце на малюнак"));
            text.put("flyUp", new StringBuilder("Сильный дракон - опасный дракон"));
            text.put("flyUpFull", new StringBuilder("ўзлятае хутчэй на 1%"));
            text.put("flyDown", new StringBuilder("Сытны цмок - цяжкі цмок"));
            text.put("flyDownFull", new StringBuilder("падае хутчэй на 1%"));
            text.put("fendOff", new StringBuilder("Спрытны цмок - жывучы цмок"));
            text.put("fendOffFull", new StringBuilder("дазваляе парыраваць ў паветры і аблятаць перашкоды"));
            text.put("roar", new StringBuilder("Люты цмок - небяспечны цмок"));
            text.put("roarFull", new StringBuilder("+1 люты роў"));
            text.put("bonusXP", new StringBuilder("Разумны цмок - хітры цмок"));
            text.put("bonusXPFull", new StringBuilder("атрыманы вопыт павялічваецца на 1%"));
            text.put("bonusHam", new StringBuilder("Галодны цмок - пражэрлівы цмок"));
            text.put("bonusHamFull", new StringBuilder("з'едзенае мяса павялічваецца на 1%"));
            text.put("buyTree", new StringBuilder("Азеляненне тэрыторыі"));
            text.put("buyTreeFull", new StringBuilder("можна пасадзіць дрэвы побач з домам"));
            text.put("buyBirds", new StringBuilder("Нябесная ахова"));
            text.put("buyBirdsFull", new StringBuilder("вырасці і выхавай сапраўдных паветраных паляўнічых"));
            text.put("buyLiveStock", new StringBuilder("Цмок цяпер фермер?"));
            text.put("buyLiveStockFull", new StringBuilder("пара разводзіць хатні жывёлу"));
            text.put("upLVL", new StringBuilder("Для адкрыцця патрэбен ўзровень дракона - "));
        }
        else {
            text.put("nullText", new StringBuilder("Click on image"));
            text.put("flyUp", new StringBuilder("A strong dragon is a dangerous dragon"));
            text.put("flyUpFull", new StringBuilder("soars faster by 1%"));
            text.put("flyDown", new StringBuilder("A nourishing dragon is a heavy dragon"));
            text.put("flyDownFull", new StringBuilder("falls faster by 1%"));
            text.put("fendOff", new StringBuilder("A quick dragon is a living dragon"));
            text.put("fendOffFull", new StringBuilder("allows you to fend in the air and fly over obstacles"));
            text.put("roar", new StringBuilder("A ferocious dragon is a dangerous dragon"));
            text.put("roarFull", new StringBuilder("+1 ferocious roar"));
            text.put("bonusXP", new StringBuilder("A smart dragon is a cunning dragon"));
            text.put("bonusXPFull", new StringBuilder("the experience gained is increased by 1%"));
            text.put("bonusHam", new StringBuilder("A hungry dragon is a gluttonous dragon"));
            text.put("bonusHamFull", new StringBuilder("eaten meat is increased by 1%"));
            text.put("buyTree", new StringBuilder("Landscaping of the territory"));
            text.put("buyTreeFull", new StringBuilder("you can plant a trees near the house"));
            text.put("buyBirds", new StringBuilder("Heavenly Protection"));
            text.put("buyBirdsFull", new StringBuilder("grow up and bring up real air hunters"));
            text.put("buyLiveStock", new StringBuilder("Is the dragon a farmer now?"));
            text.put("buyLiveStockFull", new StringBuilder("it is time to breed livestock"));
            text.put("upLVL", new StringBuilder("To open the level of the dragon - "));
        }
    }

    public void textHelp() {
        if (RuDragonGame.getPreference().loadLanguage().equals("ru")) {
            text.put("nullText", new StringBuilder("Нажмите на кого-нибудь"));
            text.put("duck", new StringBuilder("Утка - вкусное мясо"));
            text.put("tree", new StringBuilder("Дерево - следует облетать"));
            text.put("goat", new StringBuilder("Козёл - должен быть вкусным"));
            text.put("pig", new StringBuilder("Поросёнок - вкусный, жирненький, так и хочется съесть"));
            text.put("kine", new StringBuilder("Корова - большая, мясистая и очень вкусная"));
            text.put("babaYaga", new StringBuilder("Злая ведьма! Заколдует и съест"));
            text.put("birds", new StringBuilder("Хищная птица может напасть и зацарапать дракона"));
            text.put("rock", new StringBuilder("Дракону не под силу сдвинуть огромную скалу"));
        }
        else if (RuDragonGame.getPreference().loadLanguage().equals("uk")) {
            text.put("nullText", new StringBuilder("Натисніть на кого-небудь"));
            text.put("duck", new StringBuilder("Качка - смачне м'ясо"));
            text.put("tree", new StringBuilder("Дерево - слід облітати"));
            text.put("goat", new StringBuilder("Козел - повинен бути смачним"));
            text.put("pig", new StringBuilder("Порося - смачний, жирненькие, так і хочеться з'їсти"));
            text.put("kine", new StringBuilder("Корова - велика, м'ясиста і дуже смачна"));
            text.put("babaYaga", new StringBuilder("Зла відьма! Зачарує і з'їсть"));
            text.put("birds", new StringBuilder("Хижий птах може напасти і зацарапать дракона"));
            text.put("rock", new StringBuilder("Дракону не під силу зрушити величезну скелю"));
        }
        else if (RuDragonGame.getPreference().loadLanguage().equals("be")) {
            text.put("nullText", new StringBuilder("Націсніце на каго-небудзь"));
            text.put("duck", new StringBuilder("Качка - смачнае мяса"));
            text.put("tree", new StringBuilder("Дрэва - варта аблятаць"));
            text.put("goat", new StringBuilder("Казёл - павінен быць смачным"));
            text.put("pig", new StringBuilder("Поросёнок - смачны, Тлусценькая, так і хочацца з'есці"));
            text.put("kine", new StringBuilder("Карова - вялікая, мясістая і вельмі смачная"));
            text.put("babaYaga", new StringBuilder("Злая ведзьма! Зачаруй і з'есць"));
            text.put("birds", new StringBuilder("Драпежная птушка можа напасьці і зацарапать дракона"));
            text.put("rock", new StringBuilder("Цмоку не пад сілу зрушыць велізарную скалу"));
        }
        else {
            text.put("nullText", new StringBuilder("Click on someone"));
            text.put("duck", new StringBuilder("Duck is tasty meat"));
            text.put("tree", new StringBuilder("Tree - should fly around"));
            text.put("goat", new StringBuilder("Goat - should be tasty"));
            text.put("pig", new StringBuilder("Piglet is delicious, fat, so want to eat"));
            text.put("kine", new StringBuilder("Cow is big, meaty and very tasty"));
            text.put("babaYaga", new StringBuilder("Evil witch! Enchants and will eat"));
            text.put("birds", new StringBuilder("A bird of prey can attack and scratch a dragon"));
            text.put("rock", new StringBuilder("The dragon can not move a huge rock"));
        }
    }

    public void dispose() {
        try {
            textMenu.clear();
            text.clear();
        } catch (NullPointerException e) {
            // если кто не очистился
        }
    }
}