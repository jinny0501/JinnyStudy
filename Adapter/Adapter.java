    class ?????Adapter extends ArrayAdapter<?????> {

        int resource;

        public ?????Adapter(Context context, int resource, List<?????> objects) {
            super(context, resource, objects);
            this.resource = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ?????Holder holder;

            if (view == null) {
                LayoutInflater inflater = getLayoutInflater();
                view = inflater.inflate(this.resource, null);

                holder = new ?????Holder();
                ?????

                view.setTag(holder);
            }

            final ????? item = getItem(position);

            if (item != null) {
                holder = (?????Holder) view.getTag();

                ?????
            }

            return view;
        }
    }