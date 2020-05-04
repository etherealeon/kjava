import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class FractalExplorer {
    private int DisplaySize;
    private JImageDisplay Display;
    private FractalGenerator Fractal;
    private Rectangle2D.Double range;

    public FractalExplorer(int size) {
        // Здесь будет храниться значение размера отображения
        DisplaySize = size;

        // Инициализация объектов диапазона и фрактального генератора
        Fractal = new Mandelbrot();
        range = new Rectangle2D.Double();
        Fractal.getInitialRange(range);
        Display = new JImageDisplay(DisplaySize, DisplaySize); // x, y
    }

    public void createAndShowGUI() {
        Display.setLayout(new BorderLayout());
        JFrame JimageDisplay = new JFrame("Fractal Explorer");
        // Добавим объект отображения изображения в позицию CENTER
        JimageDisplay.add(Display, BorderLayout.CENTER);
        // Кнопка для сброса отображения
        JButton resetButton = new JButton("Reset");
        ButtonHandler handler = new FractalExplorer.ButtonHandler();
        resetButton.addActionListener(handler);
        // Кнопка сброса в положении SOUTH
        JimageDisplay.add(resetButton, BorderLayout.SOUTH);

        MouseHandler click = new FractalExplorer.MouseHandler();
        Display.addMouseListener(click);

        // Операция закрытия окна по умолчанию:
        JimageDisplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Создаем коллекцию фракталов
        JComboBox ComboBox = new JComboBox();
        FractalGenerator mandelbrotFractal = new Mandelbrot();
        ComboBox.addItem(mandelbrotFractal);
        FractalGenerator tricornFractal = new Tricorn();
        ComboBox.addItem(tricornFractal);
        FractalGenerator burningShipFractal = new BurningShip();
        ComboBox.addItem(burningShipFractal);
        //Создаем кнопку для выбора фрактала из коллекции
        ButtonHandler fractalChooser = new ButtonHandler();
        ComboBox.addActionListener(fractalChooser);
        JPanel DisplayPanel = new JPanel();
        JLabel myLabel = new JLabel("Fractal:");
        DisplayPanel.add(myLabel);
        DisplayPanel.add(ComboBox);
        JimageDisplay.add(DisplayPanel, BorderLayout.NORTH);

        //Создаем кнопку для сохранения изображения фрактала
        JButton saveButton = new JButton("Save");
        JPanel myBottomPanel = new JPanel();
        myBottomPanel.add(saveButton);
        myBottomPanel.add(resetButton);
        JimageDisplay.add(myBottomPanel, BorderLayout.SOUTH);

        ButtonHandler saveHandler = new ButtonHandler();
        saveButton.addActionListener(saveHandler);

        // Данные операции правильно разметят содержимое окна, сделают его
        // видимым (окна первоначально не отображаются при их создании для того,
        // чтобы можно было сконфигурировать их прежде, чем выводить на экран), и
        // затем запретят изменение размеров окна.
        JimageDisplay.pack();
        JimageDisplay.setVisible(true);
        JimageDisplay.setResizable(false);
    }

    private void drawFractal() { // Метод для вывода на экран фрактала, должен циклически проходить через каждый пиксель в отображении (т.е. значения x и y будут меняться от 0 до размера отображения)
        for (int x = 0; x < DisplaySize; x++) {
            for (int y = 0; y < DisplaySize; y++) { //x, y - пиксельная координата; xCoord, yCoord - координата в пространстве фрактала
                // Получим координаты x и у, соответствующих координатам пикселя X и У
                double xCoord = Fractal.getCoord(range.x, range.x + range.width, DisplaySize, x);
                double yCoord = Fractal.getCoord(range.y, range.y + range.height, DisplaySize, y);
                // Вычислим количество итераций для соответствующих координат в области отображения фрактала
                int iteration = Fractal.numIterations(xCoord, yCoord);

                if (iteration == -1) { // Если число итераций равно -1 (т.е. точка не выходит за границы),установим пиксель в черный цвет (для rgb значение 0).
                    Display.drawPixel(x, y, 0);
                }
                else { // Иначе выберем значение цвета, основанное на количестве итераций
                    // Воспользуемся цветовым пространством HSV: поскольку значение цвета
                    // варьируется от 0 до 1, получается плавная последовательность цветов от
                    // красного к желтому, зеленому, синему, фиолетовому и затем обратно к красному
                    float hue = 0.7f + (float) iteration / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    // Обновим отображение в соответствии с цветом для каждого пикселя
                    Display.drawPixel(x, y, rgbColor);
                }
            }

        }
        Display.repaint(); // Обновим JimageDisplay в соответствии с текущим изображением
    }

    // Внутренний класс для обработки событий от кнопок
    private class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Получение команды
            String command = e.getActionCommand();
            // Если команда - получить выпадающий список, то список выпадает, пользователь выбирает фрактал и он перерисовывается
            if (e.getSource() instanceof JComboBox) {
                JComboBox mySource = (JComboBox) e.getSource();
                Fractal = (FractalGenerator) mySource.getSelectedItem();
                Fractal.getInitialRange(range);
                drawFractal();
            }
            // Если команда сбросить фрактал - сбросить его и перерисовать
            else if (command.equals("Reset")) {
                Fractal.getInitialRange(range);
                drawFractal();
            }
            // Если команда сохранить фрактал - сохранить его ф ормате PNG на диск
            else if (command.equals("Save")) {
                // Выбрать файл для сохранения изображения
                JFileChooser myFileChooser = new JFileChooser();
                // Сохранять только в формате PNG
                FileFilter extensionFilter = new FileNameExtensionFilter("PNG Images", "png");
                myFileChooser.setFileFilter(extensionFilter);
                // Если файл хотят сохранить в формате не png, вернуть false
                myFileChooser.setAcceptAllFileFilterUsed(false);

                // Значение типа int, которое указывает результат операции выбора файла
                int userSelection = myFileChooser.showSaveDialog(Display);

                //Если метод возвращает значение JfileChooser.APPROVE_OPTION, тогда можно продолжить операцию
                // сохранения файлов, в противном случае, если пользователь отменил операцию,
                // закончить данную обработку события без сохранения
                if (userSelection == JFileChooser.APPROVE_OPTION) {

                    //Класс javax.imageio.ImageIO обеспечивает простые операции загрузки и сохранения изображения
                    java.io.File file = myFileChooser.getSelectedFile();
                    String file_name = file.toString();
                    // Попытка сохранить фрактал на диск
                    try {
                        BufferedImage displayImage = Display.getImage();
                        javax.imageio.ImageIO.write(displayImage, "png", file);
                    }
                    // Проинформировать пользователя об ошибке через диалоговое окно
                    catch (Exception exception) {
                        JOptionPane.showMessageDialog(Display, exception.getMessage(), "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                    }
                }
                // Если операция сохранения файла не APPROVE_OPTION
                else return;
            }
        }
    }

    // Внутренний класс для обработки событий с дисплея от мыши
    private class MouseHandler extends MouseAdapter {

        @Override // Переопределим метод
        // При получении события о щелчке мышью, класс должен
        // отобразить пиксельные кооринаты щелчка в область фрактала, а затем вызвать
        // метод генератора recenterAndZoomRange с координатами, по которым щелкнули, и масштабом 0.5, что приведёт к увеличению фрактала
        public void mouseClicked(MouseEvent e) {
            // Получение координаты х области щелчка мыши
            int x = e.getX();
            double xCoord = Fractal.getCoord(range.x, range.x + range.width, DisplaySize, x);
            // Получение координаты у области щелчка мыши
            int y = e.getY();
            double yCoord = Fractal.getCoord(range.y, range.y + range.height, DisplaySize, y);
            // Увеличение фрактала
            Fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            // Перерисуем фрактал
            drawFractal();
        }
    }
    public static void main(String[] args)
    {
        FractalExplorer DisplayExplorer = new FractalExplorer(600);
        DisplayExplorer.createAndShowGUI();
        DisplayExplorer.drawFractal();
    }
}