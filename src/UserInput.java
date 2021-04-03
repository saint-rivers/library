import javax.swing.*;

/**
 * Stores user inputs to be passed as arguments to other objects.
 */
class UserInput extends Console{

    static class BookInfo extends SuperBook {

        public boolean containsValidEntries() {
            return getTitle() != null
                    && getAuthor() != null
                    && getPublishedYear() != null;
        }

        public BookInfo(String[] inputs) {
            super(inputs);
        }

        @Override
        public void setAuthor(String author) {
            if (Validator.isValidEntry(author))
                super.setAuthor(author);
        }

        @Override
        public void setPublishedYear(String publishedYear) {
            if (Validator.isValidYear(publishedYear))
                super.setPublishedYear(publishedYear);
        }
    }

    static class LibraryInfo{
        String name;
        String address;

        public String getName() {
            return name.toUpperCase();
        }

        public String getAddress() {
            return address.toUpperCase();
        }

        public LibraryInfo() {
        }

        public LibraryInfo(String[] inputs) {
            this.name = inputs[0];
            this.address = inputs[1];
        }
    }
}

