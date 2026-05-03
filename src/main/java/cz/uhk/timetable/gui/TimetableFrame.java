package cz.uhk.timetable.gui;

import cz.uhk.timetable.model.Activity;
import cz.uhk.timetable.model.LocationTimetable;
import cz.uhk.timetable.model.Room;
import cz.uhk.timetable.utils.StagTimetableProvider;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

//hlavni graficke okno aplikace
public class TimetableFrame extends JFrame {
    private StagTimetableProvider timetableProvider = new StagTimetableProvider(); //umi stahovat data z internetu
    private LocationTimetable timetable = new LocationTimetable("", ""); //aktualne stazeny rozvrh
    private JTable tabTimetable; //samotna tabulka
    private TimetableModel timetableModel; //rika co ma v kterem policku robrazit

    private JComboBox<Room> comboRooms;
    private JButton btnLoad;

    public TimetableFrame() {
        super("Location Timetable");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initGui(); //vytvori graficke prvky


        loadRoomList(); // nacteni seznamu mistnosti

        pack(); //upravi velikost okna podle obsahu
        setLocationRelativeTo(null); //vycentrovani okna na stred obrazovky
    }

    private void initGui() {
        // horni panel pro vyber
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT)); //prvky se radi vedle sebe
        northPanel.add(new JLabel("Vyberte místnost:"));

        comboRooms = new JComboBox<>();
        comboRooms.setPreferredSize(new Dimension(200, 25));
        northPanel.add(comboRooms);

        btnLoad = new JButton("Načíst rozvrh");
        btnLoad.addActionListener(e -> loadData());
        northPanel.add(btnLoad);

        add(northPanel, BorderLayout.NORTH);

        // tabulka uprostred
        timetableModel = new TimetableModel();
        tabTimetable = new JTable(timetableModel);
        add(new JScrollPane(tabTimetable), BorderLayout.CENTER); //scroll
    }

    private void loadRoomList() {
        // nacte mistnosti z webu do comboboxu
        List<Room> rooms = timetableProvider.getAllRooms();
        for (Room r : rooms) {
            comboRooms.addItem(r);
        }
    }

    private void loadData() {
        Room selected = (Room) comboRooms.getSelectedItem(); //zjisti co uzivatel vybral
        if (selected != null) {
            try {
                LocationTimetable rawData = timetableProvider.readTimetable(selected.getBuilding(), selected.getRoomNumber()); //surova data z internetu

                List<Activity> filtered = rawData.getActivities().stream() //projede svechny aktivity a vybere ty ktere jsou dane
                        .filter(a -> a.getType() != null && (
                                a.getType().equalsIgnoreCase("Př") || // prednaska
                                        a.getType().equalsIgnoreCase("Cv") || // cviceni
                                        a.getType().equalsIgnoreCase("Sem")   // seminar
                        ))
                        .collect(Collectors.toList());

                timetable.setActivities(filtered); //ulozi prefiltroavna data
                timetableModel.fireTableDataChanged(); //prepise ty data
            } catch (Exception ex) {
                ex.printStackTrace(); // pomuze videt chybu v konzoli
                JOptionPane.showMessageDialog(this, "Chyba: " + ex.getMessage());
            }
        }
    }

    class TimetableModel extends AbstractTableModel {
        private final String[] COLNAMES = {"Zkratka", "Název", "Učitel", "Typ", "Den", "Od", "Do"};
        private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        @Override
        public int getRowCount() { return timetable.getActivities().size(); }

        @Override
        public int getColumnCount() { return COLNAMES.length; }

        @Override
        public String getColumnName(int col) { return COLNAMES[col]; }

        @Override
        public Object getValueAt(int row, int col) {
            Activity a = timetable.getActivities().get(row);
            return switch (col) {
                case 0 -> a.getId();
                case 1 -> a.getName();
                case 2 -> a.getTeacher();
                case 3 -> a.getType();
                case 4 -> a.getDay();
                case 5 -> a.getFrom() != null ? a.getFrom().format(timeFormatter) : ""; //pokud cas existuje, udelej format, jinak ""
                case 6 -> a.getTo() != null ? a.getTo().format(timeFormatter) : "";
                default -> null;
            };
        }
    }
}